with Ada.Text_IO;

procedure Ada_Lab is
   Arr_Size           : constant Integer := 10;
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

   task type Find_Part_Min is
      entry Start (Start_Index, End_Index : Integer);
   end Find_Part_Min;

   task body Find_Part_Min is
      Start_Index, End_Index : Integer;
      Min_Value              : Integer := Integer'Last;
      Min_Index              : Integer;
   begin
      accept Start (Start_Index, End_Index : Integer) do
         Find_Part_Min.Start_Index := Start_Index;
         Find_Part_Min.End_Index   := End_Index;
      end Start;

      for I in Start_Index .. End_Index loop
         if Min_Value > Arr (I) then
            Min_Value := Arr (I);
            Min_Index := I;
         end if;
      end loop;

      Ada.Text_IO.Put_Line
        ("Min index:" & Min_Index'Image & ", Min value:" & Min_Value'Image);
   end Find_Part_Min;

   procedure Find_Total_Min is
      Part_Size : constant Integer := Arr_Size / Thread_Num;
      Threads   : array (1 .. Thread_Num) of Find_Part_Min;
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
   end Find_Total_Min;

   protected Total_Min is
      procedure Set_Total_Min (Min_Index, Min_Value : Integer);

   private
      Total_Min_Index : Integer := 1;
      Total_Min_Value : Integer := Integer'Last;
   end Total_Min;

   protected body Total_Min is
      procedure Set_Total_Min (Min_Index, Min_Value : Integer) is
      begin
         if Total_Min_Value > Min_Index then
            Total_Min_Index := Min_Index;
            Total_Min_Value := Min_Value;
         end if;
      end Set_Total_Min;
   end Total_Min;

begin
   Init_Arr;
end Ada_Lab;
