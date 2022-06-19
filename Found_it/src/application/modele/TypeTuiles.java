package application.modele;

public enum TypeTuiles {

    sky (7),
    grass(8),
    dirt(6),
    river(0),
    bedrock(9);

    private int codeTuiles;

    TypeTuiles(int codeTuiles){
        this.codeTuiles = codeTuiles;
    }

    public int getCodeTuile(){
        return this.codeTuiles;
    }



}
// private TypeTuiles sky = TypeTuiles.sky;
// private TypeTuiles grass = TypeTuiles.grass;
// private TypeTuiles river = TypeTuiles.river;
// private TypeTuiles dirt = TypeTuiles.dirt;
// private TypeTuiles bedrock = TypeTuiles.bedrock;