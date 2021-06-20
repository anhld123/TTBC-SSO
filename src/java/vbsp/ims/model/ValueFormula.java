/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

/**
 *
 * @author lion
 */
public class ValueFormula {

    private int nOrder; //Thu tu sap xep du lieu tuong ung voi order trong bang SAVE_BODY_RPT_FORMULA
    private String sData; //Se luu data khi da tinh toan xong so lieu tu cong thuc
    private String sFieldData;  //Lieu cac field can lay du lieu vi du: (DDNO+PSNO), (DDNO+PSNO-PSCO)
    private String sFormula;//Luu cong thuc tong khi nhap tren web (TK9_2_0_704_&(DDNO+PSNO-PSCO))&/2*TK9_1_0_704_&(DDNO+PSNO)&-2+TK9_5_0_704_&PSNO&+CT1A014&GIATRI&-CT01345&GIATRI&
    private String sKey; //Luu key la dong du lieu trong bang SAVE_BODY_RPT_FORMULA
    private String sWhereFormula; //Luu dieu kien where khi select du lieu vi du tu cong thuc tren se la: TK9_2_0_704_,TK9_1_0_704_

    public ValueFormula() {

    }

    public ValueFormula(String sKey) {
        this.sKey = sKey;

    }

    public ValueFormula(String sKey, int nOrder) {
        this.sKey = sKey;
        this.nOrder = nOrder;

    }

    public ValueFormula(String sKey, int nOrder, String sFormula) {
        this.sKey = sKey;
        this.nOrder = nOrder;
        this.sFormula = sFormula;

    }

    public ValueFormula(String sKey, int nOrder, String sFormula, String sWhereFormula) {
        this.sKey = sKey;
        this.nOrder = nOrder;
        this.sFormula = sFormula;
        this.sWhereFormula = sWhereFormula;

    }

    public ValueFormula(String sKey, int nOrder, String sFormula, String sWhereFormula, String sData) {
        this.sKey = sKey;
        this.nOrder = nOrder;
        this.sFormula = sFormula;
        this.sWhereFormula = sWhereFormula;
        this.sData = sData;
    }

    /**
     * Get the value of sFieldData
     *
     * @return the value of sFieldData
     */
    public String getsFieldData() {
        return sFieldData;
    }

    /**
     * Set the value of sFieldData
     *
     * @param sFieldData new value of sFieldData
     */
    public void setsFieldData(String sFieldData) {
        this.sFieldData = sFieldData;
    }

    /**
     * Get the value of sFormula
     *
     * @return the value of sFormula
     */
    public String getsFormula() {
        return sFormula;
    }

    /**
     * Set the value of sFormula
     *
     * @param sFormula new value of sFormula
     */
    public void setsFormula(String sFormula) {
        this.sFormula = sFormula;
    }

    /**
     * Get the value of sData
     *
     * @return the value of sData
     */
    public String getsData() {
        return sData;
    }

    /**
     * Set the value of sData
     *
     * @param sData new value of sData
     */
    public void setsData(String sData) {
        this.sData = sData;
    }

    /**
     * Get the value of sKey
     *
     * @return the value of sKey
     */
    public String getsKey() {
        return sKey;
    }

    /**
     * Set the value of sKey
     *
     * @param sKey new value of sKey
     */
    public void setsKey(String sKey) {
        this.sKey = sKey;
    }

    /**
     * Get the value of nOrder
     *
     * @return the value of nOrder
     */
    public int getnOrder() {
        return nOrder;
    }

    /**
     * Set the value of nOrder
     *
     * @param nOrder new value of nOrder
     */
    public void setnOrder(int nOrder) {
        this.nOrder = nOrder;
    }

    /**
     * Get the value of sWhereFormula
     *
     * @return the value of sWhereFormula
     */
    public String getsWhereFormula() {
        return sWhereFormula;
    }

    /**
     * Set the value of sWhereFormula
     *
     * @param sWhereFormula new value of sWhereFormula
     */
    public void setsWhereFormula(String sWhereFormula) {
        this.sWhereFormula = sWhereFormula;
    }

}
