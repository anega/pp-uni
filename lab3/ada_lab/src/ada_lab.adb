with Ada.Text_IO;

procedure Ada_Lab is

   task type Producer;
   task type Consumer;

   task body Producer is
   begin
      null;
   end Producer;

   task body Consumer is
   begin
      null;
   end Consumer;

begin
   Ada.Text_IO.Put_Line ("Init");
end Ada_Lab;
