package uz.yengil_yechim.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity()
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayMeCheck {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chatId;
    public String transferType;
    public String checkNumber;
    public String cardNum;
    public String sum;
    public String contractNumber;
    public String description;
    public String checkDate;
    public String paymentId;
    public String paymentStatus;



    //O'zgardi eskisi bu
    // private Long id;
    //    private long chatId;
    //    public String transferType;
    //    public String checkNumber;
    //    public String cardNum;
    //    public BigDecimal sum;
    //    public long contractNumber;
    //    public String description;
    //    public Timestamp checkDate;
    //    public String paymentId;
    //    public String paymentStatus;



    @Override
    public String toString() {
        return "PayMeCheck{" + "\n" +
                "id=" + id + "\n" +
                ", chatId=" + chatId + "\n" +
                ", transferType='" + transferType + '\'' + "\n" +
                ", checkNumber='" + checkNumber + '\'' + "\n" +
                ", cardNum='" + cardNum + '\'' + "\n" +
                ", sum=" + sum + "\n" +
                ", contractNumber=" + contractNumber + "\n" +
                ", description='" + description + '\'' + "\n" +
                ", checkDate=" + checkDate + "\n" +
                ", paymentId='" + paymentId + '\'' + "\n" +
                ", paymentStatus='" + paymentStatus + '\'' + "\n" +
                '}';
    }
}

