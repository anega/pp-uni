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
begin
   Init_Arr;
   for I in Arr'Range loop
      Ada.Text_IO.Put (Arr (I)'Image & ",");
   end loop;
end Ada_Lab;
