/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.action;

/**
 *
 * @author BAOANH
 */
public class ModelExcel {

    private String C1;
    private String C2;
    private String C3;
    private double N1;
    private double N2;
    private double N3;
    private double N4;
    private double N5;
    private double N6;
    private double N7;
    private double N8;
    private double N9;
    private double N10;
    private double N11;
    private double N12;
    private double N13;
    private double N14;
    private double N15;
    private double N16;
    private double N17;
    private double N18;
    private double N19;
    private double N20;
    private double N21;
    private double N22;
    private double N23;
    private double N24;
    private double N25;
    private double N26;
    private double N27;
    private double N28;
    private double N29;
    private double N30;
    private double N31;
    private double N32;
    private double N33;
    private double N34;
    private double N35;
    private String Formula;

    public void setValue(int cellIndex, Object value) {
        switch (cellIndex) {
            case 0:
                String tt = String.valueOf(value);
                if (tt.indexOf(".") > 0) {
                    tt = tt.substring(0, tt.indexOf("."));
                    //macn = macn.length() < 2 ? "0" + macn : macn;
                }
                setC1(tt);
                break;
            case 1:
                String macn = String.valueOf(value);
                if (macn.indexOf(".") > 0) {
                    macn = macn.substring(0, macn.indexOf("."));
                    macn = macn.length() < 2 ? "0" + macn : macn;
                }
                setC2(macn);
                break;
            case 2:
                setC3(String.valueOf(value));
                break;
            case 3:
                setN1((double) value);
                break;
            case 4:
                setN2((double) value);
                break;
            case 5:
                setN3((double) value);
                break;
            case 6:
                setN4((double) value);
                break;
            case 7:
                setN5((double) value);
                break;
            case 8:
                setN6((double) value);
                break;
            case 9:
                setN7((double) value);
                break;
            case 10:
                setN8((double) value);
                break;
            case 11:
                setN9((double) value);
                break;
            case 12:
                setN10((double) value);
                break;
            case 13:
                setN11((double) value);
                break;
            case 14:
                setN12((double) value);
                break;
            case 15:
                setN13((double) value);
                break;
            case 16:
                setN14((double) value);
                break;
            case 17:
                setN15((double) value);
                break;
            case 18:
                setN16((double) value);
                break;
            case 19:
                setN17((double) value);
                break;
            case 20:
                setN18((double) value);
                break;
            case 21:
                setN19((double) value);
                break;
            case 22:
                setN20((double) value);
                break;
            case 23:
                setN21((double) value);
                break;
            case 24:
                setN22((double) value);
                break;
            case 25:
                setN23((double) value);
                break;
            case 26:
                setN24((double) value);
                break;
            case 27:
                setN25((double) value);
                break;
            case 28:
                setN26((double) value);
                break;
            case 29:
                setN27((double) value);
                break;
            case 30:
                setN28((double) value);
                break;
            case 31:
                setN29((double) value);
                break;
            case 32:
                setN30((double) value);
                break;
            case 33:
                setN31((double) value);
                break;
            case 34:
                setN32((double) value);
                break;
            case 35:
                setN33((double) value);
                break;
            case 36:
                setN34((double) value);
                break;
            case 37:
                setN35((double) value);
                break;

            default:
                break;
        }
    }

    public String getC1() {
        return C1;
    }

    public void setC1(String C1) {
        this.C1 = C1;
    }

    public String getC2() {
        return C2;
    }

    public void setC2(String C2) {
        this.C2 = C2;
    }

    public String getC3() {
        return C3;
    }

    public void setC3(String C3) {
        this.C3 = C3;
    }

    public double getN1() {
        return N1;
    }

    public void setN1(double N1) {
        this.N1 = N1;
    }

    public double getN2() {
        return N2;
    }

    public void setN2(double N2) {
        this.N2 = N2;
    }

    public double getN3() {
        return N3;
    }

    public void setN3(double N3) {
        this.N3 = N3;
    }

    public double getN4() {
        return N4;
    }

    public void setN4(double N4) {
        this.N4 = N4;
    }

    public double getN5() {
        return N5;
    }

    public void setN5(double N5) {
        this.N5 = N5;
    }

    public double getN6() {
        return N6;
    }

    public void setN6(double N6) {
        this.N6 = N6;
    }

    public double getN7() {
        return N7;
    }

    public void setN7(double N7) {
        this.N7 = N7;
    }

    public double getN8() {
        return N8;
    }

    public void setN8(double N8) {
        this.N8 = N8;
    }

    public double getN9() {
        return N9;
    }

    public void setN9(double N9) {
        this.N9 = N9;
    }

    public double getN10() {
        return N10;
    }

    public void setN10(double N10) {
        this.N10 = N10;
    }

    public double getN11() {
        return N11;
    }

    public void setN11(double N11) {
        this.N11 = N11;
    }

    public double getN12() {
        return N12;
    }

    public void setN12(double N12) {
        this.N12 = N12;
    }

    public double getN13() {
        return N13;
    }

    public void setN13(double N13) {
        this.N13 = N13;
    }

    public double getN14() {
        return N14;
    }

    public void setN14(double N14) {
        this.N14 = N14;
    }

    public double getN15() {
        return N15;
    }

    public void setN15(double N15) {
        this.N15 = N15;
    }

    public double getN16() {
        return N16;
    }

    public void setN16(double N16) {
        this.N16 = N16;
    }

    public double getN17() {
        return N17;
    }

    public void setN17(double N17) {
        this.N17 = N17;
    }

    public double getN18() {
        return N18;
    }

    public void setN18(double N18) {
        this.N18 = N18;
    }

    public double getN19() {
        return N19;
    }

    public void setN19(double N19) {
        this.N19 = N19;
    }

    public double getN20() {
        return N20;
    }

    public void setN20(double N20) {
        this.N20 = N20;
    }

    public double getN21() {
        return N21;
    }

    public void setN21(double N21) {
        this.N21 = N21;
    }

    public double getN22() {
        return N22;
    }

    public void setN22(double N22) {
        this.N22 = N22;
    }

    public double getN23() {
        return N23;
    }

    public void setN23(double N23) {
        this.N23 = N23;
    }

    public double getN24() {
        return N24;
    }

    public void setN24(double N24) {
        this.N24 = N24;
    }

    public double getN25() {
        return N25;
    }

    public void setN25(double N25) {
        this.N25 = N25;
    }

    public double getN26() {
        return N26;
    }

    public void setN26(double N26) {
        this.N26 = N26;
    }

    public double getN27() {
        return N27;
    }

    public void setN27(double N27) {
        this.N27 = N27;
    }

    public double getN28() {
        return N28;
    }

    public void setN28(double N28) {
        this.N28 = N28;
    }

    public double getN29() {
        return N29;
    }

    public void setN29(double N29) {
        this.N29 = N29;
    }

    public double getN30() {
        return N30;
    }

    public void setN30(double N30) {
        this.N30 = N30;
    }

    public double getN31() {
        return N31;
    }

    public void setN31(double N31) {
        this.N31 = N31;
    }

    public double getN32() {
        return N32;
    }

    public void setN32(double N32) {
        this.N32 = N32;
    }

    public double getN33() {
        return N33;
    }

    public void setN33(double N33) {
        this.N33 = N33;
    }

    public double getN34() {
        return N34;
    }

    public void setN34(double N34) {
        this.N34 = N34;
    }

    public double getN35() {
        return N35;
    }

    public void setN35(double N35) {
        this.N35 = N35;
    }

    
    
    public String getFormula() {
        return Formula;
    }

    public void setFormula(String Formula) {
        this.Formula = Formula;
    }

}
