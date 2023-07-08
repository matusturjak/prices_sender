package sk.matusturjak.price_sender.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto implements Serializable {
    private String name;
    private double normalCost;
    private double actualCost;
    private Date validTo;
    private String pictureURL;
    private String superDepartment;
    private String department;
}
