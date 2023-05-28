with Ada.Text_IO;

procedure Ada_Lab is
   Arr_Size           : constant Integer := 100_000;
   Smallest_Num       : constant Integer := -1;
   Smallest_Num_Index : constant Integer := 4;
   Thread_Num         : constant Integer := 3;

   Arr : array (1 .. Arr_Size) of Integer;

   procedure Init_Arr is
   begin
      for I in 1 .. Arr_Size loop
         if I = Smallest_Num_Index then
            Arr (I) := Smallest_Num;
            goto Continue;
         end if;
         Arr (I) := I;
         <<Continue>>
      end loop;
   end Init_Arr;

   protected Total_Min is
      procedure Set_Total_Min (Min_Index, Min_Value : Integer);
      entry Get_Total_Min (Total_Min_Index, Total_Min_Value : out Integer);

   private
      Curr_Min_Index       : Integer := 1;
      Curr_Min_Value       : Integer := Integer'Last;
      Completed_Task_Count : Integer := 0;
   end Total_Min;

   protected body Total_Min is
      procedure Set_Total_Min (Min_Index, Min_Value : Integer) is
      begin
         if Curr_Min_Value > Min_Value then
            Curr_Min_Index := Min_Index;
            Curr_Min_Value := Min_Value;
         end if;
         Completed_Task_Count := Completed_Task_Count + 1;
      end Set_Total_Min;

      entry Get_Total_Min (Total_Min_Index, Total_Min_Value : out Integer)
        when Completed_Task_Count = Thread_Num is
      begin
         Total_Min_Index := Curr_Min_Index;
         Total_Min_Value := Curr_Min_Value;
      end Get_Total_Min;
   end Total_Min;

   task type Find_Part_Min is
      entry Start (Start_Index, End_Index : Integer);
   end Find_Part_Min;

   task body Find_Part_Min is
      Start_Index, End_Index : Integer;
      Part_Min_Value         : Integer := Integer'Last;
      Part_Min_Index         : Integer;
   begin
      accept Start (Start_Index, End_Index : Integer) do
         Find_Part_Min.Start_Index := Start_Index;
         Find_Part_Min.End_Index   := End_Index;
      end Start;

      for I in Start_Index .. End_Index loop
         if Part_Min_Value > Arr (I) then
            Part_Min_Value := Arr (I);
            Part_Min_Index := I;
         end if;
      end loop;

      Total_Min.Set_Total_Min (Part_Min_Index, Part_Min_Value);
   end Find_Part_Min;

   procedure Find_Total_Min is
      Threads         : array (1 .. Thread_Num) of Find_Part_Min;
      Part_Size       : constant Integer := Arr_Size / Thread_Num;
      Total_Min_Index : Integer          := 0;
      Total_Min_Value : Integer          := Integer'Last;
   begin
      for I in 1 .. Thread_Num loop
         declare
            Start_Index : constant Integer := (I - 1) * Part_Size + 1;
            End_Index   : Integer          := I * Part_Size;
         begin
            if I = Thread_Num then
               End_Index := Arr_Size;
            end if;
            Threads (I).Start (Start_Index, End_Index);
         end;
      end loop;
      Total_Min.Get_Total_Min (Total_Min_Index, Total_Min_Value);
      Ada.Text_IO.Put_Line
        ("Min array value:" & Total_Min_Value'Image & " value index:" &
         Total_Min_Index'Image);
   end Find_Total_Min;

begin
   Init_Arr;
   Find_Total_Min;
end Ada_Lab;
