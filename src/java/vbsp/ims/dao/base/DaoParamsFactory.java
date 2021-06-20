/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao.base;

/**
 *
 * @author Le Duc Hung
 */
public class DaoParamsFactory {
    public static DaoParamsI createDaoParam(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException{
        if(className == null || className.isEmpty())
            return (DaoParamsI) Class.forName("vbsp.ims.dao.implement.DaoParamsImp").newInstance();
        else
            return (DaoParamsI) Class.forName(className).newInstance();
    }
}
