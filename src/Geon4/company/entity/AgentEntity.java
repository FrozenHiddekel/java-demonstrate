package Geon4.company.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgentEntity {
    private int id;
    private String title;
    private String agentType;
    private String address;
    private String phone;
    private String email;
    private String logo;
    private double priority;

    public AgentEntity(String title, String agentType, String address, String phone, String email, String logo, double priority) {
        this.id = -2;
        this.title = title;
        this.agentType = agentType;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.logo = logo;
        this.priority = priority;
    }
}
