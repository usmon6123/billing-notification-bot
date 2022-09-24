package uz.yengil_yechim.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayMeCheckDto {
    public String transferType;
    public String checkNumber;
    public String cardNum;
    public String sum;
    public String contractNumber;
    public String description;
    public String checkDate;
    public String paymentId;
    public String paymentStatus;



    @Override
    public String toString() {
        return "{" +
                "\"transferType\":\"" + transferType + '\"' +
                ", \"checkNumber\":\"" + checkNumber + '\"' +
                ", \"cardNum\":\"" + cardNum + '\"' +
                ", \"sum\":\"" + sum + '\"' +
                ", \"contractNumber\":\"" + contractNumber + '\"' +
                ", \"description\":\"" + description + '\"' +
                ", \"checkDate\":\"" + checkDate + '\"' +
                ", \"paymentId\":\"" + paymentId + '\"' +
                ", \"paymentStatus\":\"" + paymentStatus + '\"' +
                '}';
    }
}
