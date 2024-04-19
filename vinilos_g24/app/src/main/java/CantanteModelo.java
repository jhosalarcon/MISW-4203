public class CantanteModelo {
    private String nombreAlbum, cantante;
    private int imgAlbum;

    public CantanteModelo() {
    }

    public CantanteModelo(String nombreAlbum, String cantante, int imgAlbum) {
        this.nombreAlbum = nombreAlbum;
        this.cantante = cantante;
        this.imgAlbum = imgAlbum;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    public int getImgAlbum() {
        return imgAlbum;
    }

    public void setImgAlbum(int imgAlbum) {
        this.imgAlbum = imgAlbum;
    }
}
