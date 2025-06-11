package org.vivek.platform.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vivek.platform.Model.Codechef.Codechef;
import org.vivek.platform.Model.Codeforces.Codeforces;
import org.vivek.platform.Model.Leetcode.Leetcode;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String cchandle;
    private String cfhandle;
    private String lchandle;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Leetcode lc;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Codeforces cf;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Codechef cc;



}
