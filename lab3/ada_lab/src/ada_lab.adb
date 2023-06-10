with Ada.Text_IO;                                   use Ada.Text_IO;
with GNAT.Semaphores;                               use GNAT.Semaphores;
with Ada.Containers.Indefinite_Doubly_Linked_Lists; use Ada.Containers;

procedure Ada_Lab is
   package String_Lists is new Indefinite_Doubly_Linked_Lists (String);
   use String_Lists;

   Req_Count      : constant Integer := 15;
   Storage_Size   : constant Integer := 2;
   Storage        : List;
   Access_Storage : Counting_Semaphore (1, Default_Ceiling);
   Empty          : Counting_Semaphore (0, Default_Ceiling);
   Full           : Counting_Semaphore (Storage_Size, Default_Ceiling);

   task type Producer;
   task type Consumer;

   task body Producer is
   begin
      for I in 1 .. Req_Count loop
         Full.Seize;
         Access_Storage.Seize;
         Storage.Append ("Item " & I'Image);
         Put_Line ("Item" & I'Image & " produced.");
         Access_Storage.Release;
         Empty.Release;
      end loop;
   end Producer;

   task body Consumer is
   begin
      for J in 1 .. Req_Count loop
         Empty.Seize;
         Access_Storage.Seize;
         declare
            item : constant String := First_Element (Storage);
         begin
            Put_Line ("Item" & item & " consumed.");
         end;
         Storage.Delete_First;
         Access_Storage.Release;
         Full.Release;
      end loop;
   end Consumer;

   P : Producer;
   C : Consumer;

begin
   null;
end Ada_Lab;
