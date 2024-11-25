# Project Specification for Group #111

Team Name: Chat Messaging System (CMS)
Domain:
Chat System – A platform for sending, receiving, and organizing messages
between users or in group chats.

Software Specification:
The program should allow users to:
● Create a chat account with a valid username and password and log in/log
out of the account
● Send and receive text messages in real-time.
● Create group chats with multiple users.
● Edit or delete sent messages.
● Search for messages by keyword, user, or timestamp.
● Use a third-party API for additional functionality (e.g, using real-time
notifications).

User Stories:
1. Mia wants to create an account. She creates an account by entering a username
and password into the chatroom. The system returns if the username is valid
(does not already exist) and if the password is strong enough.
2. Alice creates a new group chat. She invites her friends and sends the first
message. All invited users see the message in real-time.
3. Carol is part of multiple group chats and wants to search for a message so she
uses the search bar to find a message by keyword.
4. Michael wants to edit a message he sent in a group chat so he clicks on the edit
option, modifies his message, and saves it.

5. Sophia accidentally sent a message. She wants to delete the message so she
clicks on the delete button on the message. The message disappears from the
chat.
6. The CMS development team wants to design and implement a user-friendly
interface for account creation, group chat functionality, and message searching
so users can easily interact with the chat system.

Proposed Entities for the Domain:
Message
● String content
● User sender
● DateTime timestamp
● boolean edited
● ChatRoom chatRoom
ChatRoom
● String name
● List<User> participants
● List<Message> messages
User
● String username
● String password
● List<ChatRoom> chatRooms
● boolean onlineStatus

Proposed API for the project:
Firebase Realtime Database API

**Dividing Tasks**
- [ ] User Story 2 + Entities - Chiraz Belarbi 
- [ ] User Story 3/4 - Ismail Iraz 
- [ ] User Story 5/6 - Joojo Botchway 
- [ ] API - Ali Gill
● Used to store and synchronize chat data across users in real-time.

● Allows users to access chat rooms and messages seamlessly
across devices.

Scheduled Meeting Times + Mode of Communication:
[when will your team meet each week — you MUST meet during the weekly tutorial
timeslot and we strongly recommend scheduling one more regular meeting time]
Meeting time outside of lab: Mondays 3-4pm
Mode of Communication: Discord
