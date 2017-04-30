package com.adscoop.userservice.entites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by thokle on 24/08/2016.
 */
@NodeEntity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true)
public class UserNode extends Entity {

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String middlename;

    @Getter
    @Setter
    private boolean isActivated;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String token;

    @Setter
    @Getter
    @Labels
    @Builder.Default
    private List<String> labels = new ArrayList<>();

    @Setter
    @Getter

    @Relationship(direction = Relationship.OUTGOING, type = "USER_HAS_CREDITINFO")
    @Builder.Default
    private Set<CreditInfo> creditInfos = new HashSet<>();

    @Setter
    @Getter
    @Relationship(direction = Relationship.OUTGOING, type = "USER_HAS_COMPANY")
    @Builder.Default
    private Set<Company> companyNodes = new HashSet<>();

    @Setter
    @Getter
    @Relationship(direction = Relationship.OUTGOING, type = "USER_HAS_ADDRESS")
    @Builder.Default
    private Set<AddressNode> addressNodes = new HashSet<>();

    @Setter
    @Getter
    @Relationship(direction = Relationship.OUTGOING, type = "HAS_ACCOUNT_INFORMATION")
    @Builder.Default
    private Set<AccountInformation> accountInformations = new HashSet<>();

    @Setter
    @Getter
    @Relationship(direction = Relationship.OUTGOING, type = "HAS_BANNER_NODES")
    @Builder.Default
    private Set<BannerNode> bannerNodes = new HashSet<>();


    @Relationship(direction = Relationship.OUTGOING, type = "USER_HAS_WEBSITE")
    @Builder.Default
    private Set<WebSiteNode> webSiteNodes = new HashSet<>();

    @Getter
    @Setter
    private boolean activated;

    public void addCreditInfo(CreditInfo creditInfo) {
        this.creditInfos.add(creditInfo);
        creditInfo.getUserNodeSet().add(this);
    }

    public void setAccountInformation(AccountInformation accountInformation) {
        this.accountInformations.add(accountInformation);
        accountInformation.getUserNodeSet().add(this);
    }

    public void addAddress(AddressNode addressNode) {
        addressNodes.add(addressNode);
        addressNode.getUserNodes().add(this);
    }

    public UserNode activatedUser() {
        this.activated = true;
        return this;
    }

    public void addWebSite(WebSiteNode webSiteNode) {
        webSiteNodes.add(webSiteNode);
        webSiteNode.getUserNodes().add(this);
    }


}
