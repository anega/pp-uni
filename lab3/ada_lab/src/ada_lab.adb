with Ada.Text_IO;                                   use Ada.Text_IO;
with Ada.Containers.Indefinite_Doubly_Linked_Lists; use Ada.Containers;

procedure Ada_Lab is
   package String_Lists is new Indefinite_Doubly_Linked_Lists (String);
   use String_Lists;

   Req_Count : constant Integer := 3;
   Storage   : List;

   task type Producer;
   task type Consumer;

   task body Producer is
   begin
      for I in 1 .. Req_Count loop
         Storage.Append ("Item " & I'Image);
         Put_Line ("Item" & I'Image & " produced.");
      end loop;
   end Producer;

   task body Consumer is
   begin
      null;
   end Consumer;

   P : Producer;

begin
   Put_Line ("Init");
end Ada_Lab;
