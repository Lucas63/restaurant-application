//package me.billsays.restaurant.client.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
///**
// * UserCheckElement.java 8/30/16, 2016
// *
// * @author mkvitko
// */
//@Entity
//@Table(name = "Usercheckelement1")
//@Data
//@AllArgsConstructor
//@EqualsAndHashCode
//@NoArgsConstructor
//@Builder
//public class UserCheckElement {
//    private Double cost;
//    @ManyToOne
//    @JoinColumn(name = "user1id_user", referencedColumnName = "id_user", nullable = false)
//    private User user;
//    @ManyToOne
//    @JoinColumn(name = "checkelement1id_check_element", referencedColumnName = "id_check_element", nullable = false)
//    private CheckElement checkElement;
//}
