package cn.edu.seu.kse.project.ontology.channel.translator.tbox.reducer;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import cn.edu.seu.kse.project.ontology.channel.connector.OWLSimpleConnector;
import cn.edu.seu.kse.project.ontology.channel.translator.tbox.OWLSubClassOfAxiomTranslator;
import cn.edu.seu.kse.project.ontology.exception.OntoException;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectIntersectionOfAxiom;

public class OWLObjectIntersectionOfReducer implements OWLClassExpressionReducer {

	private OWLSimpleConnector connector;
	
	private OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator;
	
	public OWLObjectIntersectionOfReducer(
			OWLSimpleConnector connector,
			OWLSubClassOfAxiomTranslator subClassOfAxiomTranslator){
		
		this.connector = connector;

		this.subClassOfAxiomTranslator = subClassOfAxiomTranslator;
		
	}
	
	
	@Override
	public void reduceSubClassExpression(OWLSubClassOfAxiom subClassOfAxiom) {
		
		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		if(subClassExpression instanceof OWLObjectIntersectionOf) {
			OWLObjectIntersectionOf intersectionOf = (OWLObjectIntersectionOf) subClassExpression;	
			Set<OWLClassExpression> conjunctionClassExpressions =  intersectionOf.asConjunctSet();
			
			OWLClassExpression processConjunct = null; 
			for(OWLClassExpression conjunct : conjunctionClassExpressions) {
				if(processConjunct == null) processConjunct = conjunct;
				if(!(conjunct instanceof OWLClass)) {
					processConjunct = conjunct; break;
				}
			}
			
			// there exists a complex class in the conjunction.
			if(!(processConjunct instanceof OWLClass)) {
				OWLClass auxiliaryClass = connector.getAllocater().allocateOWLClass(processConjunct);
				
				conjunctionClassExpressions.remove(processConjunct);
				conjunctionClassExpressions.add(auxiliaryClass);
				OWLObjectIntersectionOf newConjuntion = 
						connector.getOWLDataFactory().getOWLObjectIntersectionOf(conjunctionClassExpressions);			
				OWLSubClassOfAxiom newConjunctionAxiom = 
						connector.getOWLDataFactory().getOWLSubClassOfAxiom(
						newConjuntion, superClassExpression);
				
				this.reduceSubClassExpression(newConjunctionAxiom);
				
				OWLSubClassOfAxiom newSubClassOfAxiom = 
						connector.getOWLDataFactory().getOWLSubClassOfAxiom(
						processConjunct, auxiliaryClass);
				subClassOfAxiomTranslator.translate(newSubClassOfAxiom);
				
			} 
			// all the classes in the conjunction are named classes.
			else {
				
				if(conjunctionClassExpressions.size() == 2) {
					register(subClassOfAxiom);
				
				} else if(conjunctionClassExpressions.size() > 2) {
					conjunctionClassExpressions.remove(processConjunct);
					OWLObjectIntersectionOf newConjuntion = 
							connector.getOWLDataFactory().getOWLObjectIntersectionOf(conjunctionClassExpressions);
									
					OWLClass auxiliaryClass = 
							connector.getAllocater().allocateOWLClass(newConjuntion);
					
					OWLSubClassOfAxiom newConjunctionAxiom = 
							connector.getOWLDataFactory().getOWLSubClassOfAxiom(
							newConjuntion, auxiliaryClass);
					this.reduceSubClassExpression(newConjunctionAxiom);
					
					OWLObjectIntersectionOf processedConjunction = 
							connector.getOWLDataFactory().getOWLObjectIntersectionOf(
							processConjunct, auxiliaryClass);
					OWLSubClassOfAxiom processedConjunctionAxiom = 
							connector.getOWLDataFactory().getOWLSubClassOfAxiom(
							processedConjunction, superClassExpression);
					
					this.register(processedConjunctionAxiom);
					
				}	
			}
		}	
	}

	@Override
	public void reduceSuperClassExpression(OWLSubClassOfAxiom subClassOfAxiom) {
		
		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		if(superClassExpression instanceof OWLObjectIntersectionOf) {
			OWLObjectIntersectionOf intersectionOf = (OWLObjectIntersectionOf) superClassExpression;	
			Set<OWLClassExpression> conjunctionClassExpressions =  intersectionOf.asConjunctSet();
			
			for(OWLClassExpression conjunct : conjunctionClassExpressions) {
				OWLSubClassOfAxiom newSubClassOfAxiom = 
						connector.getOWLDataFactory().getOWLSubClassOfAxiom(
						subClassExpression, conjunct);
				subClassOfAxiomTranslator.translate(newSubClassOfAxiom);	
			}
		}
	}

	@Override
	public void register(OWLSubClassOfAxiom subClassOfAxiom) {
		
		OWLClassExpression subClassExpression = subClassOfAxiom.getSubClass();
		OWLClassExpression superClassExpression = subClassOfAxiom.getSuperClass();
		
		if(subClassExpression instanceof OWLObjectIntersectionOf) {
			OWLObjectIntersectionOf intersectionOf = (OWLObjectIntersectionOf) subClassExpression;
			Set<OWLClassExpression> conjectSet = intersectionOf.asConjunctSet();
				
			if(conjectSet.size() != 2) {
				try {
					throw new OntoException("There exist more than two concepts" +
							" in the conjunct of " + subClassOfAxiom.toString());
				} catch (OntoException e) {
					e.printStackTrace();
				}
			} else {
				OWLClassExpression subClassExpression1 = null;
				OWLClassExpression subClassExpression2 = null;
				
				int _i = 0;
				for(OWLClassExpression subClass : conjectSet) {
					if(_i == 0) { 
						subClassExpression1 = subClass; _i ++;
					} else if(_i == 1) {
						subClassExpression2 = subClass; _i ++;
					}
				}
				
				if(subClassExpression1 instanceof OWLClass
						&& subClassExpression2 instanceof OWLClass
						&& superClassExpression instanceof OWLClass) {
					Integer subClassEntry1 = connector.getAllocater()
							.allocateClassIntegerEntry((OWLClass)subClassExpression1);
					Integer subClassEntry2 = connector.getAllocater()
							.allocateClassIntegerEntry((OWLClass)subClassExpression2);
					Integer superClassEntry = connector.getAllocater()
							.allocateClassIntegerEntry((OWLClass)superClassExpression);
					
					SimpleObjectIntersectionOfAxiom simpleObjectIntersectionOfAxiom =
							connector.getSimpleAxiomFactory().getSimpleObjectIntersectionOfAxiom(
									subClassEntry1, subClassEntry2, superClassEntry);
					
					connector.register(simpleObjectIntersectionOfAxiom);
				
				} else {
					try {
						throw new OntoException("There exists non-named class" +
								" in " + subClassOfAxiom.toString());
					} catch (OntoException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			try {
				throw new OntoException("The subclass is not an intersect concept" +
						": " + subClassOfAxiom.toString());
			} catch (OntoException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	

}
