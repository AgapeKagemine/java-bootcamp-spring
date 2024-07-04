package com.bootcamp.onetomany.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "blogList")
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OWNER_DETAILS")
public class Owner {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "email")
        private String email;
        
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
        private List<Blog> blogList;

        public Owner(String name, String email) {
            this.name = name;
            this.email = email;
        }    
}
