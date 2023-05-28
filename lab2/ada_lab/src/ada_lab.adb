with Ada.Text_IO;

procedure Ada_Lab is
   Arr_Size           : constant Integer := 9;
   Smallest_Num       : constant Integer := -1;
   Smallest_Num_Index : constant Integer := 4;

   Arr : array (0 .. Arr_Size) of Integer;

   procedure Init_Arr is
   begin
      for I in 0 .. Arr_Size loop
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
   end Find_Part_Min;

   --  TODO: Call min calc in boundaries and set result to a global variable
   --  TODO: Organize syncronized set to global min variable

begin
   Init_Arr;
end Ada_Lab;
