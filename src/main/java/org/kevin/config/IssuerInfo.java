package org.kevin.config;

import io.quarkus.arc.All;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssuerInfo {
    private String issuer;
    private String email;
    private String website;
}

