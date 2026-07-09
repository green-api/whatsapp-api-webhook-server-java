package com.greenapi.server.examples;

import com.greenapi.client.pkg.api.webhook.WebhookHandler;
import com.greenapi.client.pkg.models.notifications.*;
import org.springframework.stereotype.Component;
import com.greenapi.client.pkg.models.notifications.messages.EditedMessage;
import com.greenapi.client.pkg.models.notifications.messages.InteractiveButtonsMessage;
import com.greenapi.client.pkg.models.notifications.messages.InteractiveButtonsReplyMessage;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Component(value = "whatsappWebhookHandler")
@Log4j2
class WebhookHandlerExample implements WebhookHandler {

    @SneakyThrows
    @Override
    public void handle(Notification notification) {
        var body = notification.getBody();

        if (body instanceof TextMessageWebhook textMessage) {
            var text = textMessage.getMessageData().getTextMessageData().getTextMessage();
            log.info("Incoming text message from {}: {}", textMessage.getSenderData().getSender(), text);

        } else if (body instanceof ExtendedTextMessageWebhook extMessage) {
            var text = extMessage.getMessageData().getExtendedTextMessageData().getText();
            log.info("Incoming extended text message: {}", text);

        } else if (body instanceof FileMessageWebhook fileMessage) {
            var fileData = fileMessage.getMessageData().getFileMessageData();
            log.info("Incoming file: {} ({})", fileData.getFileName(), fileData.getMimeType());

        } else if (body instanceof LocationMessageWebhook locationMessage) {
            var loc = locationMessage.getMessageData().getLocationMessageData();
            log.info("Incoming location: lat={}, lon={}", loc.getLatitude(), loc.getLongitude());

        } else if (body instanceof ContactMessageWebhook contactMessage) {
            var contact = contactMessage.getMessageData().getContactMessageData();
            log.info("Incoming contact: {}", contact.getDisplayName());

        } else if (body instanceof ContactsArrayMessageWebhook contactsArray) {
            var contacts = contactsArray.getMessageData().getMessageData().getContacts();
            log.info("Incoming contacts array, count: {}", contacts.size());

        } else if (body instanceof ReactionMessageWebhook reactionMessage) {
            var reaction = reactionMessage.getMessageData().getExtendedTextMessageData().getText();
            log.info("Incoming reaction: {}", reaction);

        } else if (body instanceof StickerMessageWebhook stickerMessage) {
            var sticker = stickerMessage.getMessageData().getFileMessageData();
            log.info("Incoming sticker: animated={}", sticker.getIsAnimated());

        } else if (body instanceof PollMessageWebhook pollMessage) {
            var poll = pollMessage.getMessageData().getPollMessageData();
            log.info("Incoming poll '{}' with {} options", poll.getName(), poll.getOptions().size());

        } else if (body instanceof PollUpdateMessageWebhook pollUpdate) {
            var update = pollUpdate.getMessageData().getPollMessageData();
            log.info("Poll update '{}': {} vote options", update.getName(), update.getVotes().size());

        } else if (body instanceof GroupInviteMessageWebhook groupInvite) {
            var invite = groupInvite.getMessageData();
            log.info("Incoming group invite to: {}", invite.getGroupName());

        } else if (body instanceof EditedMessageWebhook editedMessage) {
            EditedMessage data = editedMessage.getMessageData();
            log.info("Message edited, new text: '{}', original id: {}",
                data.getEditedMessageData().getTextMessage(),
                data.getEditedMessageData().getStanzaId());

        } else if (body instanceof DeletedMessageWebhook deletedMessage) {
            var deletedId = deletedMessage.getMessageData().getDeletedMessageData().getStanzaId();
            log.info("Message deleted, original id: {}", deletedId);

        } else if (body instanceof InteractiveButtonsMessageWebhook interactiveButtons) {
            InteractiveButtonsMessage data = interactiveButtons.getMessageData();
            var buttons = data.getInteractiveButtons();
            log.info("Incoming interactive buttons message: '{}', buttons count: {}",
                buttons.getContentText(),
                buttons.getButtons().size());

        } else if (body instanceof InteractiveButtonsReplyMessageWebhook interactiveReply) {
            InteractiveButtonsReplyMessage data = interactiveReply.getMessageData();
            var reply = data.getTemplateButtonReplyMessage();
            log.info("Interactive button selected: id='{}', text='{}'",
                reply.getSelectedId(),
                reply.getSelectedDisplayText());

        } else if (body instanceof ButtonsMessageWebhook buttonsMessage) {
            log.info("Incoming buttons message");

        } else if (body instanceof ButtonsResponseMessageWebhook buttonsResponse) {
            log.info("Buttons response received from: {}", buttonsResponse.getSenderData().getSender());

        } else if (body instanceof ListMessageWebhook listMessage) {
            log.info("Incoming list message");

        } else if (body instanceof ListResponseMessageWebhook listResponse) {
            log.info("List item selected");

        } else if (body instanceof TemplateMessageWebhook templateMessage) {
            log.info("Incoming template message");

        } else if (body instanceof TemplateButtonsReplyMessageWebhook templateReply) {
            log.info("Template button selected: {}", templateReply.getMessageData().getSelectedDisplayText());

        } else if (body instanceof QuotedMessageWebhook quotedMessage) {
            log.info("Incoming quoted message");

        } else if (body instanceof OutgoingMessageStatus outgoingStatus) {
            log.info("Outgoing message status: {} -> {}", outgoingStatus.getIdMessage(), outgoingStatus.getStatus());

        } else if (body instanceof IncomingCall incomingCall) {
            log.info("Incoming call from: {}, status: {}, isVideo: {}",
                incomingCall.getFrom(),
                incomingCall.getStatus(),
                incomingCall.getIsVideo());

        } else if (body instanceof OutgoingCall outgoingCall) {
            log.info("Outgoing call to: {}, status: {}, duration: {}s",
                outgoingCall.getFrom(),
                outgoingCall.getStatus(),
                outgoingCall.getDuration());

        } else if (body instanceof QuotaExceeded quotaExceeded) {
            var quota = quotaExceeded.getQuotaData();
            log.warn("Quota exceeded! Used: {}/{}, status: {}",
                quota.getUsed(),
                quota.getTotal(),
                quota.getStatus());

        } else if (body instanceof StateInstanceChanged stateChanged) {
            log.info("Instance status changed: {}", stateChanged.getStatusInstance());

        } else if (body instanceof StatusInstanceChanged statusChanged) {
            log.info("Instance state changed: {}", statusChanged.getStateInstance());

        } else {
            log.warn("Unhandled notification type: {}", body != null ? body.getTypeWebhook() : "null");
        }
    }
}
