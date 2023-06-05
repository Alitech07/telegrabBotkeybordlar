package org.example.service;


import org.example.controller.BotController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;

public class KreditCalculs {
    public SendMessage getCreditCommand(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Bizda kreditlarni 0 dan 50 million so'mgacha\n 1 oydan 48 oygacha muddatda\n yillik foiz stavkasi 27.9% dan 29.9% gacha miqdorda olishingiz mumkin.");
        InlineKeyboardButton cal =new InlineKeyboardButton();
        cal.setText("Kredit Kalkulyatori");
        cal.setCallbackData("calculs");

        List<InlineKeyboardButton> row = new LinkedList<>();
        row.add(cal);

        List<List<InlineKeyboardButton>> rowCollection = new LinkedList<>();
        rowCollection.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowCollection);

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
    public SendMessage getKreditCalclus(Double sum,Integer month, Double protsent, Long chatId){
        SendMessage sendMessage = new SendMessage();
            Double residual = sum;
            double principl_debt = round((sum/month)*100)/100.0;
            String table = "";

            for (int i=1;i<=month;i++){
                double y = residual*protsent/100;
                double interest_payment = round(((residual-y)/12)*100)/100.0;
                double monthly_payment = round((principl_debt+interest_payment)*100)/100.0;
                    table +=
                            "  <b>"+i+"</b>  "
                            +"   <b>"+residual+"</b>    "
                            +"    <b>"+principl_debt+"</b>   "
                            +"<b>   "+interest_payment+" </b>  "
                            +"<b>   "+monthly_payment+" </b>"
                            +"\n";
                residual=round((residual-principl_debt)*100)/100.0;

            }

            String text = "      <b><i>Kredit malumotlari</i></b>     \n"
                            +"<b> Oy </b>"
                            + "<b> Qoldiq summasi </b> "
                            +"<b> Asosiy qarz </b>"
                            +"<b> Foiz to'lovi </b> "
                            +"<b> Oylik to'lov </b>"
                        +"\n"
                        +table;


            sendMessage.setChatId(chatId);
            sendMessage.setText(text);
            sendMessage.setParseMode("HTML");
            return sendMessage;
    }
}
