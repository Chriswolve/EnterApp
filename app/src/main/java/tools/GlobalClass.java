package tools;

/**
 * Created by Cristhian on 5/21/16.
 */
import android.app.Application;

public class GlobalClass extends Application {

    // Variables
    private String codtra_rep = "",
            nomtra_rep = "",
            turtra_rep ="",
            fec_rep="VAR_FECHA",
            hor_rep="VAR_HORA",
            tip_rep="",
            areori_rep="",
            areobs_rep="",
            des_rep="",
            tarrea_cco="",
            bentra_cco="",
            catdes_cco="",
            razdes_cco="",
            accsug_cco="",
            urlima_fot="";

    // Getter Nombre




    public GlobalClass(){

    }

    public void setCodtra_rep(String codtra_rep) {
        this.codtra_rep = codtra_rep;
    }

    public void setNomtra_rep(String nomtra_rep) {
        this.nomtra_rep = nomtra_rep;
    }

    public void setTurtra_rep(String turtra_rep) {
        this.turtra_rep = turtra_rep;
    }

    public void setFec_rep(String fec_rep) {
        this.fec_rep = fec_rep;
    }

    public void setHor_rep(String hor_rep) {
        this.hor_rep = hor_rep;
    }

    public void setTip_rep(String tip_rep) {
        this.tip_rep = tip_rep;
    }

    public void setAreori_rep(String areori_rep) {
        this.areori_rep = areori_rep;
    }

    public void setAreobs_rep(String areobs_rep) {
        this.areobs_rep = areobs_rep;
    }

    public void setDes_rep(String des_rep) {
        this.des_rep = des_rep;
    }

    public void setTarrea_cco(String tarrea_cco) {
        this.tarrea_cco = tarrea_cco;
    }

    public void setBentra_cco(String bentra_cco) {
        this.bentra_cco = bentra_cco;
    }

    public void setCatdes_cco(String catdes_cco) {
        this.catdes_cco = catdes_cco ;
    }

    public void setRazdes_cco(String razdes_cco) {
        this.razdes_cco = razdes_cco ;
    }

    public void setUrlima_fot(String urlima_fot) {
        this.urlima_fot = urlima_fot;
    }

    public String getCodtra_rep() {
        return codtra_rep;
    }

    public String getNomtra_rep() {
        return nomtra_rep;
    }

    public String getTurtra_rep() {
        return turtra_rep;
    }

    public String getFec_rep() {
        return fec_rep;
    }

    public String getHor_rep() {
        return hor_rep;
    }

    public String getTip_rep() {
        return tip_rep;
    }

    public String getAreori_rep() {
        return areori_rep;
    }

    public String getAreobs_rep() {
        return areobs_rep;
    }

    public String getDes_rep() {
        return des_rep;
    }

    public String getTarrea_cco() {
        return tarrea_cco;
    }

    public String getBentra_cco() {
        return bentra_cco;
    }

    public String getCatdes_cco() {
        return catdes_cco;
    }

    public String getRazdes_cco() {
        return razdes_cco;
    }

    public String getUrlima_fot() {
        return urlima_fot;
    }

    public String getAccsug_cco() {
        return accsug_cco;
    }

    public void setAccsug_cco(String accsug_cco) {
        this.accsug_cco = accsug_cco;
    }


    public String getCCOData(){
        return "'"+codtra_rep+"','"+nomtra_rep+"','"+turtra_rep+"','"+fec_rep+"','"+hor_rep+"','CCO','"+areori_rep+"','"+areobs_rep+"','"+des_rep+"','"+tarrea_cco+"','"+bentra_cco+"','"+catdes_cco+"','"+razdes_cco+"','"+accsug_cco+"','"+urlima_fot+"'";
    }
}