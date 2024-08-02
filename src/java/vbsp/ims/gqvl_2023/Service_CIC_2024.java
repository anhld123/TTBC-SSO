/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import java.text.SimpleDateFormat;
import vbsp.ims.restapi.*;
import vbsp.ims.eps.epsModel;
import vbsp.ims.khnv2021.PosClass;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HP
 */
public class Service_CIC_2024 {

    DuLieuNTService_CIC _service = new DuLieuNTService_CIC();

    public ApiFileCic_Tmp getFileCic(String id) {

        try {
            {
                return _service.getFileCic(id);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
