/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Bernhard
 */
@Entity
@Table
@PrimaryKeyJoinColumn(name="InvoiceCopy_ID")
public class InvoiceCopy extends Document{


    
}
