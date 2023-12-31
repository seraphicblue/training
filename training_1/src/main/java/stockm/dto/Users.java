package stockm.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Table(name = "Users")
@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    private String name;
    private String email;
    private String password;
   

    @Builder
    public Users(Long userid, String name, String email, String password) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Users update(String name) {
        this.name = name;
        return this;
    }

	public Users setPassword(String encryptedPassword) {
	    this.password=encryptedPassword;
	    return this;
		
	}

	public void setEmail(String email) {
		this.email = email;
		
	}

   
}