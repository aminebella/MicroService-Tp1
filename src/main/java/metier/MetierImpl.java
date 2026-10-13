package metier;

import dao.IDao;

public class MetierImpl implements IMetier {
    private IDao dao;

    public void setDao(IDao dao){
        this.dao = dao;
    }

    @Override
    public double calcul() {
        if(dao == null){
            throw new IllegalStateException("Dao n'a pas été injecté.");
        }
        double value = dao.getValue();
        double result = value * 2;
        System.out.println("[METIER]: Calcul effectue. Voici le resultat: " + result);
        return result;
    }
}
