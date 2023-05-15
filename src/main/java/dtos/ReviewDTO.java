package dtos;

import entities.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewDTO {
    private long id;
    private String name;
    private String description;
    private String userName;



    public ReviewDTO() {
    }

    public ReviewDTO(String name, String description, String userName) {
        this.name = name;
        this.description = description;
        this.userName = userName;
    }

    public ReviewDTO(Review rm) {
        if (rm.getId() != null)
            this.id = rm.getId();
        this.name = rm.getName();
        this.description = rm.getDescription();
        this.userName = rm.getUser().getUserName();

    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public static List<ReviewDTO> getDtos(List<Review> rms) {
        List<ReviewDTO> rmdtos = new ArrayList<>();
        rms.forEach(rm -> rmdtos.add(new ReviewDTO(rm)));
        return rmdtos;
    }
}