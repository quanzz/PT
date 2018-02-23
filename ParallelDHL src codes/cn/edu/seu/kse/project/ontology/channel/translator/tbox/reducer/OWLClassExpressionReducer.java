package cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer;

import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

public interface OWLClassExpressionReducer {
	
	public void reduceSubClassExpression(OWLSubClassOfAxiom subClassOfAxiom);
	
	public void reduceSuperClassExpression(OWLSubClassOfAxiom subClassOfAxiom);
	
	public void register(OWLSubClassOfAxiom subClassOfAxiom);

}
