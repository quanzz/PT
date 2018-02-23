package cn.edu.seu.kse.project.materializer.ontology;

import cn.edu.seu.kse.project.toolkit.Tool;

public class DHLOntologyStatisticer {
	
	private DHLOntologyBase ontoBase;
	
	private DHLIndex index;
	
	public DHLOntologyStatisticer(DHLOntologyBase ontoBase) {
		this.ontoBase = ontoBase;
		this.index = this.ontoBase.getIndex();
	}
	
	public void printReport(){
		
		Tool.pl("Statistics:");
		Tool.pl("TBox");
		Tool.pl("numOfDHLSubClassOfAxiom: " + index.subClassOfAxiomSet.size());
		Tool.pl("numOfDHLLeftExistentialAxiom: " + index.leftExistentialAxiomSet.size());
		Tool.pl("numOfDHLObjectIntersectionOfAxiom: " + index.intersectionOfAxiomSet.size());
		Tool.pl("RBox");
		Tool.pl("numOfDHLObjectSubPropertyOfAxiom: " + index.subPropertyOfAxiomSet.size());
		Tool.pl("numOfDHLSubPropertyChainOfAxiom: " + index.subPropertyChainOfAxioms.size());
		Tool.pl("numOfDHLTransitiveObjectPropertyAxiom: " + index.transitiveRoles.size());
		Tool.pl("ABox");
		Tool.pl("numOfDHLClassAssertion: " + index.classAssertionSet.size());
		Tool.pl("numOfDHLObjectPropertyAssertion: " + index.propertyAssertionSet.size());
		Tool.pl("------------------------------");
	}

}
