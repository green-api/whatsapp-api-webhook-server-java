# SDK API inventory: 0.1.8

Every method below is present in the `whatsapp-api-client-java-0.1.8` source JAR. Read the corresponding page in the official [GREEN-API API index](https://green-api.com/en/docs/api/) before using it; that page is authoritative for parameters, responses, limits, and errors.

| Group | Implemented public methods |
|---|---|
| account | `getSettings()`, `getWaSettings()`, `setSetting(InstanceSettingsReq)`, `getStateInstance()`, `getStatusInstance()`, `reboot()`, `logout()`, `getQrCode()`, `setProfilePicture(File)`, `getAuthorizationCode(Long)` |
| groups | `createGroup(CreateGroupReq)`, `updateGroupName(ChangeGroupNameReq)`, `getGroupData(String)`, `addGroupParticipant(ChangeParticipantReq)`, `removeGroupParticipant(ChangeParticipantReq)`, `setGroupAdmin(ChangeParticipantReq)`, `removeGroupAdmin(ChangeParticipantReq)`, `setGroupPicture(ChangeGroupPictureReq)`, `leaveGroup(String)` |
| journals | `getChatHistory(GetChatHistoryReq)`, `getMessage(MessageReq)`, `lastIncomingMessages(Integer)`, `lastOutgoingMessages(Integer)` |
| queues | `showMessagesQueue()`, `clearMessagesQueue()` |
| marking | `readChat(MessageReq)` |
| service | `checkWhatsapp(Long)`, `getAvatar(String)`, `getContacts()`, `getContactInfo(String)`, `deleteMessage(MessageReq)`, `archiveChat(String)`, `unarchiveChat(String)`, `setDisappearingChat(String, Long)` |
| sending | `sendMessage(OutgoingMessage)`, `sendButtons(OutgoingButtons)`, `sendTemplateButtons(OutgoingTemplateButtons)`, `sendListMessage(OutgoingListMessage)`, `sendContact(OutgoingContact)`, `sendFileByUpload(OutgoingFileByUpload)`, `sendFileByUrl(OutgoingFileByUrl)`, `uploadFile(File)`, `sendLocation(OutgoingLocation)`, `sendPoll(OutgoingPoll)` |
| receiving | `receiveNotification()`, `deleteNotification(Integer)`, `downloadFile(MessageReq)` |

Do not write Java SDK code for a REST method absent from this table. `NotificationMapper.get(String)`, `WebhookConsumer.start(WebhookHandler)`, `WebhookConsumer.stop()`, and `WebhookHandler.handle(Notification)` are also present SDK helpers.
