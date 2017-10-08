/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.managed.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author romanrudenko
 */
@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {

    public LocaleBean() {
    }

    private String localeCode;
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private static final Map<String, Object> countries;

    static {
        countries = new LinkedHashMap<>();
        countries.put("en", Locale.ENGLISH);
        countries.put("ru", new Locale("ru"));
    }

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void countryLocaleCodeChanged(ValueChangeEvent e) {

        String newLocaleValue = e.getNewValue().toString();

        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if (entry.getValue().toString().equals(newLocaleValue)) {

                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale) entry.getValue());
                setLocaleCode(newLocaleValue);

            }
        }
    }

    public void selLocaleRU() {
        locale = new Locale("ru");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        setLocaleCode("ru");
    }

    public void selLocaleEN() {
        locale = new Locale("en");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        setLocaleCode("en");
    }

}
