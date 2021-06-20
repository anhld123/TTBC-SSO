/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemcn;

import vbsp.ims.chamdiemtt.action.*;

/**
 *
 * @author BAOANH
 */
public class ModelExcelFile {

    private String C1;
    private String C2;
    private String C3;
    private String N1;
    private String N2;
    private String N3;
    private String N4;
    private String N5;
    private String N6;
    private String N7;
    private String N8;
    private String N9;
    private String N10;
    private String N11;
    private String N12;
    private String N13;
    private String N14;
    private String N15;
    private String N16;
    private String N17;
    private String N18;
    private String N19;
    private String N20;
    private String N21;
    private String N22;
    private String N23;
    private String N24;
    private String N25;
    private String N26;
    private String N27;
    private String N28;
    private String N29;
    private String N30;
    private String N31;
    private String N32;
    private String N33;
    private String N34;
    private String N35;
    private String Formula;
    
    public static ModelExcelFile newInstance() {
        return new ModelExcelFile();
    }

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
                setN1(String.valueOf(value));
                break;
            case 4:
                setN2(String.valueOf(value));
                break;
            case 5:
                setN3(String.valueOf(value));
                break;
            case 6:
                setN4(String.valueOf(value));
                break;
            case 7:
                setN5(String.valueOf(value));
                break;
            case 8:
                setN6(String.valueOf(value));
                break;
            case 9:
                setN7(String.valueOf(value));
                break;
            case 10:
                setN8(String.valueOf(value));
                break;
            case 11:
                setN9(String.valueOf(value));
                break;
            case 12:
                setN10(String.valueOf(value));
                break;
            case 13:
                setN11(String.valueOf(value));
                break;
            case 14:
                setN12(String.valueOf(value));
                break;
            case 15:
                setN13(String.valueOf(value));
                break;
            case 16:
                setN14(String.valueOf(value));
                break;
            case 17:
                setN15(String.valueOf(value));
                break;
            case 18:
                setN16(String.valueOf(value));
                break;
            case 19:
                setN17(String.valueOf(value));
                break;
            case 20:
                setN18(String.valueOf(value));
                break;
            case 21:
                setN19(String.valueOf(value));
                break;
            case 22:
                setN20(String.valueOf(value));
                break;
            case 23:
                setN21(String.valueOf(value));
                break;
            case 24:
                setN22(String.valueOf(value));
                break;
            case 25:
                setN23(String.valueOf(value));
                break;
            case 26:
                setN24(String.valueOf(value));
                break;
            case 27:
                setN25(String.valueOf(value));
                break;
            case 28:
                setN26(String.valueOf(value));
                break;
            case 29:
                setN27(String.valueOf(value));
                break;
            case 30:
                setN28(String.valueOf(value));
                break;
            case 31:
                setN29(String.valueOf(value));
                break;
            case 32:
                setN30(String.valueOf(value));
                break;
            case 33:
                setN31(String.valueOf(value));
                break;
            case 34:
                setN32(String.valueOf(value));
                break;
            case 35:
                setN33(String.valueOf(value));
                break;
            case 36:
                setN34(String.valueOf(value));
                break;
            case 37:
                setN35(String.valueOf(value));
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

    public String getN1() {
        return N1;
    }

    public void setN1(String N1) {
        this.N1 = N1;
    }

    public String getN2() {
        return N2;
    }

    public void setN2(String N2) {
        this.N2 = N2;
    }

    public String getN3() {
        return N3;
    }

    public void setN3(String N3) {
        this.N3 = N3;
    }

    public String getN4() {
        return N4;
    }

    public void setN4(String N4) {
        this.N4 = N4;
    }

    public String getN5() {
        return N5;
    }

    public void setN5(String N5) {
        this.N5 = N5;
    }

    public String getN6() {
        return N6;
    }

    public void setN6(String N6) {
        this.N6 = N6;
    }

    public String getN7() {
        return N7;
    }

    public void setN7(String N7) {
        this.N7 = N7;
    }

    public String getN8() {
        return N8;
    }

    public void setN8(String N8) {
        this.N8 = N8;
    }

    public String getN9() {
        return N9;
    }

    public void setN9(String N9) {
        this.N9 = N9;
    }

    public String getN10() {
        return N10;
    }

    public void setN10(String N10) {
        this.N10 = N10;
    }

    public String getN11() {
        return N11;
    }

    public void setN11(String N11) {
        this.N11 = N11;
    }

    public String getN12() {
        return N12;
    }

    public void setN12(String N12) {
        this.N12 = N12;
    }

    public String getN13() {
        return N13;
    }

    public void setN13(String N13) {
        this.N13 = N13;
    }

    public String getN14() {
        return N14;
    }

    public void setN14(String N14) {
        this.N14 = N14;
    }

    public String getN15() {
        return N15;
    }

    public void setN15(String N15) {
        this.N15 = N15;
    }

    public String getN16() {
        return N16;
    }

    public void setN16(String N16) {
        this.N16 = N16;
    }

    public String getN17() {
        return N17;
    }

    public void setN17(String N17) {
        this.N17 = N17;
    }

    public String getN18() {
        return N18;
    }

    public void setN18(String N18) {
        this.N18 = N18;
    }

    public String getN19() {
        return N19;
    }

    public void setN19(String N19) {
        this.N19 = N19;
    }

    public String getN20() {
        return N20;
    }

    public void setN20(String N20) {
        this.N20 = N20;
    }

    public String getN21() {
        return N21;
    }

    public void setN21(String N21) {
        this.N21 = N21;
    }

    public String getN22() {
        return N22;
    }

    public void setN22(String N22) {
        this.N22 = N22;
    }

    public String getN23() {
        return N23;
    }

    public void setN23(String N23) {
        this.N23 = N23;
    }

    public String getN24() {
        return N24;
    }

    public void setN24(String N24) {
        this.N24 = N24;
    }

    public String getN25() {
        return N25;
    }

    public void setN25(String N25) {
        this.N25 = N25;
    }

    public String getN26() {
        return N26;
    }

    public void setN26(String N26) {
        this.N26 = N26;
    }

    public String getN27() {
        return N27;
    }

    public void setN27(String N27) {
        this.N27 = N27;
    }

    public String getN28() {
        return N28;
    }

    public void setN28(String N28) {
        this.N28 = N28;
    }

    public String getN29() {
        return N29;
    }

    public void setN29(String N29) {
        this.N29 = N29;
    }

    public String getN30() {
        return N30;
    }

    public void setN30(String N30) {
        this.N30 = N30;
    }

    public String getN31() {
        return N31;
    }

    public void setN31(String N31) {
        this.N31 = N31;
    }

    public String getN32() {
        return N32;
    }

    public void setN32(String N32) {
        this.N32 = N32;
    }

    public String getN33() {
        return N33;
    }

    public void setN33(String N33) {
        this.N33 = N33;
    }

    public String getN34() {
        return N34;
    }

    public void setN34(String N34) {
        this.N34 = N34;
    }

    public String getN35() {
        return N35;
    }

    public void setN35(String N35) {
        this.N35 = N35;
    }

    

    
    
    public String getFormula() {
        return Formula;
    }

    public void setFormula(String Formula) {
        this.Formula = Formula;
    }

    
}
