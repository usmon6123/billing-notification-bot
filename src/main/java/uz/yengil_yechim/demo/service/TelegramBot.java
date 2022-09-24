package uz.yengil_yechim.demo.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.yengil_yechim.demo.config.BotConfig;
import uz.yengil_yechim.demo.entity.PayMeCheck;
import uz.yengil_yechim.demo.payload.PayMeCheckDto;
import uz.yengil_yechim.demo.repository.PayMeCheckRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TelegramBot extends TelegramLongPollingBot {


    @Autowired
    private PayMeCheckRepository payMeCheckRepository;

    final BotConfig config;


    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if ( !messageText.isEmpty()) {

                //MESSAGENI DTOGA O'GIRB OLDIK
                PayMeCheckDto payMeCheckDto = messagePayMeToDto(chatId, messageText);

                PayMeCheck payMeCheck = dtoToPayMeCheck(chatId, payMeCheckDto);

                payMeCheckRepository.save(payMeCheck);

                sendMessage(chatId, payMeCheck.toString());
            }
        }
    }


    private PayMeCheckDto messagePayMeToDto(long chatId, String message) {
        String[] split = message.split("\n");
        //MESSAGE ICHIDAN SUMMANI QIRQIB OLYAPMIZ
        String sum = split[3]
                .replace("\uD83C\uDDFA\uD83C\uDDFF", "")
                .replace(",", ".")
                .replace("сум", " ");

        //MESSAGEDA KELGAN MA'LUMOTLARNI DTOGA O'GIRIB QAYTARYAPMIZ
        return new PayMeCheckDto(
                split[0].replace("\uD83D\\uDCCD  ", " ").substring(3),
                split[1].replace("\uD83E\\uDDFE  ", " ").substring(3),
                split[2].replace("\uD83D\uDCB3  ", " ").substring(3),
                sum.substring(1, sum.length() - 2),
                split[4].replace("\uD83D\uDD38  ", " ").substring(19),
                split[5].replace("\uD83D\uDCDD  ", " ").substring(3),
                split[6].replace("\uD83D\uDD53 ", " ").substring(1),
                split[7].replace("\uD83C\uDD94 ", " ").substring(3),
                split[8].replace("✅ ", " ").substring(2)
        );
    }

    @SneakyThrows
    private PayMeCheck dtoToPayMeCheck(Long chatId, PayMeCheckDto payMeCheckDto) {

        BigDecimal sum = new BigDecimal(payMeCheckDto.getSum());
        long contractNumber = Long.parseLong(payMeCheckDto.getContractNumber());

        String sDate = payMeCheckDto.getCheckDate();//18:32:34 22.09.2022 HH:mm:ss dd.MM.YYYY
        Date date1 = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").parse(sDate);
        System.out.println(sDate + "\t" + date1);


        PayMeCheck payMeCheck = new PayMeCheck();
        payMeCheck.setChatId(chatId);
        payMeCheck.setTransferType(payMeCheckDto.getTransferType());
        payMeCheck.setCheckNumber(payMeCheckDto.getCheckNumber());
        payMeCheck.setCardNum(payMeCheckDto.getCardNum());
        payMeCheck.setSum(sum);
        payMeCheck.setContractNumber(contractNumber);
        payMeCheck.setDescription(payMeCheckDto.getDescription());
        payMeCheck.setCheckDate(new Timestamp(date1.getTime()));
        payMeCheck.setPaymentId(payMeCheckDto.getPaymentId());
        payMeCheck.setPaymentStatus(payMeCheckDto.getPaymentStatus());

        return payMeCheck;


    }


    //FOYDALANUVCHIGA JAVOB QAYTARADIGAN METHOD
    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
