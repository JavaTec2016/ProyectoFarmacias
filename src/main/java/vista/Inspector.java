package vista;

import java.util.ArrayList;
import java.util.Objects;

public interface Inspector {
    public static int[] intersectar(int[] a, int[] b){
        int[] mayor, menor, res;
        ArrayList<Integer> tmp = new ArrayList();
        if(a.length < b.length){
            mayor = b;
            menor = a;
        }else {
            mayor = a;
            menor = b;
        }
        for (int i = 0, j = 0; i < mayor.length && j < menor.length;) {
            if(mayor[i] > mayor[j]) j++;
            else {
                if (mayor[i] == menor[j]) tmp.add(mayor[i]);
                i++;
            }
        }
        res = new int[tmp.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }

    /**
     * Filtra una lista de valores en base a los indices dados
     * @param lista lista de valores a filtrar
     * @param indices lista de indices admitidos
     * @return lista de valores filtrados
     */
    public static Object[] filtrarIndices(Object[] lista, int[] indices){
        Object[] out = new Object[indices.length];
        for(int i = 0; i < indices.length; i++){
            out[i] = lista[indices[i]];
        }
        return out;
    }
    public static int[] ubicarCamposCoincidencia(String[] obtenido, String match){
        ArrayList<Integer> out = new ArrayList<>();
        int[] idxs;
        int i = 0;
        for(String dato : obtenido){
            if(Objects.equals(dato, match)) out.add(i);
            i++;
        }

        i = 0;
        idxs = new int[out.size()];
        for(int d : out){
            idxs[i] = out.get(i);
            i++;
        }
        return idxs;
    }
    public static int[] ubicarCamposValidos(Object[] obtenido){
        ArrayList<Integer> out = new ArrayList<>();
        int[] idxs;
        int i = 0;
        for(Object dato : obtenido){
            if(dato != null) out.add(i);
            i++;
        }

        i = 0;
        idxs = new int[out.size()];
        for(int d : out){
            idxs[i] = out.get(i);
            i++;
        }
        return idxs;
    }

}
