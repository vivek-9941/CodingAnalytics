package org.vivek.platform.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    private Long id;
    private String username;
    private String password;
    private String email;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Leetcode lc;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Codeforces cf;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Codechef cc;


}
