package Controller.Semantica;

import Model.Arreglo;
import Model.Variable;
import Vista.Pantalla;

/**
 *
 * @author Gonza
 */
public class Etapa_2 {

    private Arreglo raiz = new Arreglo();
    private Arreglo ultimo = new Arreglo();
    private final Pantalla p;

    public Etapa_2(Pantalla p) {
        this.p = p;
    }

    public void empezar(Variable var) {
        raiz.addVar(var);
        ultimo = raiz;
    }

    public void addItem(Variable var) {
        ultimo.addVar(var);
    }

    public void addNodo() {
        Arreglo nuevo = new Arreglo();
        nuevo.setPrevio(ultimo);
        ultimo.setNext(nuevo);
        ultimo = nuevo;
    }

    public void addItem(String op) {
        Variable var = new Variable();
        var.getId().add(op);
        ultimo.addOper(var);
    }

    @SuppressWarnings("empty-statement")
    public void resolver() {
        Etapa_1 s1 = new Etapa_1(p);
        ultimo.getVars().forEach(i -> {
            i.getId().forEach(j -> {
                if (i.isVariable()) {
                    s1.getIds().add(i);
                } else if (i.isOperador()) {
                    s1.getOperadores().add(i.getId().getLast());
                }
            });
        });
        if (ultimo.getPrevio() != null) {
            ultimo = ultimo.getPrevio();
            ultimo.getLast().remDim();
            ultimo.setNext(null);
            boolean tercera = true;
            if (ultimo.getTarr() > -1) {
                System.out.println("Dimension aceptada");
                if (s1.getIds().size() > 1) {
                    tercera = false;
                } else if (s1.getIds().size() == 1) {
                    try {
                        Integer.parseInt(s1.getIds().getFirst().getId().getFirst());
                        tercera = true;
                    } catch (NumberFormatException e) {
                        tercera = false; 
                    } catch (Exception e) {
                        tercera = false; 
                    }
                }
                s1.Resolver();
                if (s1.getIds().getFirst().getTipo().equals("INT")) {
                    System.out.println("Valor OK");
                    if (tercera) {
                        System.out.println("Revisar");
                    } else {
                        System.out.println("Ranfo og");
                    }
                } else {
                    System.out.println("Tipo nel");
                }
            } else {
                System.out.println("Dimension dengeada");
            }
        }
        System.out.println("*********");
    }

    public Arreglo getRaiz() {
        return raiz;
    }

    public void setRaiz(Arreglo raiz) {
        this.raiz = raiz;
    }

}