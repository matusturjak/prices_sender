package sk.matusturjak.price_sender.model.tesco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperDepartment {
    private int id;
    private String name;
    private List<Department> departments;
}
