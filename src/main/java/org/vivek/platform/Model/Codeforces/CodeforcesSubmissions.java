package org.vivek.platform.Model.Codeforces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vivek.platform.Model.User;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeforcesSubmissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int compilationError;
    private int MemeoryLimit;
    private int OK;
    private int RuntimeError;
    private int TimeLimit;
    private int WrongAnswer;

    private int _800;
    private int _900;
    private int _1000;
    private int _1100;
    private int _1200;
    private int _1300;
    private int _1400;
    private int _1500;
    private int _1600;
    private int _1700;
    private int _1800;

    private LocalDateTime localDateTime;

    @OneToOne
    @JsonIgnore
    private User user;
}
