with Ada.Text_IO;

procedure Ada_Lab is
   Can_Stop : Boolean := False;
   pragma Atomic (Can_Stop);

   task type Break_Thread;
   task type Main_Thread (Thread_Id : Integer);

   task body Break_Thread is
   begin
      delay 1.0;
      Can_Stop := True;
   end Break_Thread;

   task body Main_Thread is
      Sum : Long_Long_Integer := 0;
   begin
      loop
         Sum := Sum + 1;
         exit when Can_Stop;
      end loop;
      delay 1.0;

      Ada.Text_IO.Put_Line ("Thread" & Thread_Id'Img & ": sum =" & Sum'Img);
   end Main_Thread;

   B1 : Break_Thread;
   T1 : Main_Thread (Thread_Id => 2);
   T2 : Main_Thread (Thread_Id => 1);
   T3 : Main_Thread (Thread_Id => 3);
   T4 : Main_Thread (Thread_Id => 4);
begin
   null;
end Ada_Lab;
