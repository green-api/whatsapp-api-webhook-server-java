# Sending and files

Read each official page before implementation.

| SDK call | Official page | SDK request fields | Body on success |
|---|---|---|---|
| `sending.sendMessage(OutgoingMessage)` | [SendMessage](https://green-api.com/en/docs/api/sending/SendMessage/) | inherited `chatId`, `quotedMessageId`; `message`, `linkPreview` | `SendMessageResp.idMessage` |
| `sending.sendFileByUpload(OutgoingFileByUpload)` | [SendFileByUpload](https://green-api.com/en/docs/api/sending/SendFileByUpload/) | inherited `chatId`, `quotedMessageId`; `file`, `fileName`, `caption` | `SendFileByUploadResp.idMessage`, `urlFile` |
| `sending.sendFileByUrl(OutgoingFileByUrl)` | [SendFileByUrl](https://green-api.com/en/docs/api/sending/SendFileByUrl/) | inherited `chatId`, `quotedMessageId`; `urlFile`, `fileName`, `caption` | `SendMessageResp.idMessage` |
| `sending.uploadFile(File)` | [UploadFile](https://green-api.com/en/docs/api/sending/UploadFile/) | file argument | `UploadFileResp.urlFile` |
| `sending.sendContact(OutgoingContact)` | [SendContact](https://green-api.com/en/docs/api/sending/SendContact/) | DTO fields | `SendMessageResp.idMessage` |
| `sending.sendLocation(OutgoingLocation)` | [SendLocation](https://green-api.com/en/docs/api/sending/SendLocation/) | DTO fields | `SendMessageResp.idMessage` |
| `sending.sendPoll(OutgoingPoll)` | [SendPoll](https://green-api.com/en/docs/api/sending/SendPoll/) | DTO fields | `SendMessageResp.idMessage` |

The source also implements `sendButtons(OutgoingButtons)`, `sendTemplateButtons(OutgoingTemplateButtons)`, and `sendListMessage(OutgoingListMessage)`; source comments warn that each currently returns HTTP 403. Do not use them for a first working integration.

Official constraints: SendMessage accepts emoji, has a 20,000-character maximum, and requires UTF-8 without BOM. Quote only within the target chat. For mailing or unreliable external storage, upload once with `uploadFile(File)` and pass returned `urlFile` to `sendFileByUrl`. Queue delivery rate follows the instance Message sending delay setting.
