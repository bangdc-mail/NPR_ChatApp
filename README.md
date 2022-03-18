# NPR_ChatApp
This is a 2-way chat application for network programming

Server-side
Open the ChatServer app. 
Choose a port you wish to Open.
Click "Listen" and wait for the client side to connect.

Client-side
Open the ChatClient app.
Type the Server's IP (default 127.0.0.1 as localhost).
Choose the port Server side has oppened.
Click "Connect".

==================================================================

Review:
I.	PROGRAM EXPLAINATION
In this project, any Compiler Program is acceptable. However, for personal preference, I have used Eclipse. With the extension WindowBuilder from Eclipse, Java Swing – designed to create a GUI is used to build the JFrame class. The 2 window form of the 2 programs (server side and client side) are created as such:

 ![image](https://user-images.githubusercontent.com/78629757/158966189-8326ffc2-e864-4d31-ac65-f432174c54ea.png)

Fig. 1: Server side window
 ![image](https://user-images.githubusercontent.com/78629757/158966195-dc0cf09e-792f-49b1-98f5-e528fbc55de6.png)

Fig. 2: Client side window
Tied with the “Listen”, “Connect” and “Send” button are functions actionPerfomed() in order to link with the methods in a class called ChatMessageSocket. This class (ChatMessageSocket.java) was created to establish the send and receiving function of the program, as well as the initiation of a socket connection between the server host and the client.

II. Discussion
The program is focused on simplicity, thus there are still a lot of improvement can be done to the program. Moreover, after testing the program, there are some notable drawbacks. The first notable disadvantage is that the program is compiled using JavaSE-14 and so it is not yet backward compatible with older Java versions. This can be fixed by cleaning the program to match the older versions, or to update the computer’s java compiler to the newest version. Secondly, it is advisable that the messages should be encrypted for security reasons. In situations that require a secure connection, an overhaul of the program is recommended. Lastly, the messages Message Text Field of the program is not automatically deleted after a successful transmission and require mouse clicking to send. As a result, it can be discouraging to be forced to manually delete the message in order to send a new one. However, this minor feature can be fixed by adding more function.
