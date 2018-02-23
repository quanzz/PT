package cn.edu.seu.kse.project.materializer.ontology;

import cn.edu.seu.kse.project.materializer.ontology.structure.DHLAxiomFactory;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLClassAssertion;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLLeftExistentialAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectIntersectionOfAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLObjectPropertyAssertion;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLSubClassOfAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLSubObjectPropertyOfAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLSubPropertyChainOfAxiom;
import cn.edu.seu.kse.project.materializer.ontology.structure.DHLTransitiveObjectPropertyAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleAxiomVisitor;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleClassAssertion;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleLeftExistentialAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectIntersectionOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleObjectPropertyAssertion;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSomeValuesFromAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubClassOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubObjectPropertyOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleSubPropertyChainOfAxiom;
import cn.edu.seu.kse.project.ontology.simple.structure.SimpleTransitiveObjectPropertyAxiom;

public class SimpleDHLTranslator implements SimpleAxiomVisitor {
	
	private DHLAxiomFactory axiomFactory;
	private DHLOntologyBase ontologyBase;
	private DHLBaseAdditor additor;
	
	public SimpleDHLTranslator(DHLOntologyBase ontologyBase){
		this.ontologyBase = ontologyBase;
		this.additor = ontologyBase.getBaseAdditor();
		this.axiomFactory = this.ontologyBase.getAxiomFactory();
	}
	
	// TBox

	@Override
	public void visit(SimpleLeftExistentialAxiom arg) {
		
		DHLLeftExistentialAxiom axiom = 
				axiomFactory.getDHLLeftExistentialAxiom(arg);

		additor.addAxiom(axiom);
	}

	@Override
	public void visit(SimpleObjectIntersectionOfAxiom arg) {
		DHLObjectIntersectionOfAxiom axiom =
				axiomFactory.getDHLObjectIntersectionOfAxiom(arg);
		
		additor.addAxiom(axiom);
		
	}

	@Override
	public void visit(SimpleSubClassOfAxiom arg) {

		DHLSubClassOfAxiom axiom =
				axiomFactory.getDHLSubClassOfAxiom(arg);
		
		additor.addAxiom(axiom);
		
	}
	
	@Override
	public void visit(SimpleSomeValuesFromAxiom arg) {
		
	}
	
	// RBox

	@Override
	public void visit(SimpleSubObjectPropertyOfAxiom arg) {
		
		DHLSubObjectPropertyOfAxiom axiom =
				axiomFactory.getDHLSubObjectPropertyOfAxiom(arg);
		
		additor.addAxiom(axiom);
		
	}

	@Override
	public void visit(SimpleSubPropertyChainOfAxiom arg) {

		DHLSubPropertyChainOfAxiom axiom =
				axiomFactory.getDHLSubPropertyChainOfAxiom(arg);
		
		additor.addAxiom(axiom);
		
	}

	@Override
	public void visit(SimpleTransitiveObjectPropertyAxiom arg) {

		DHLTransitiveObjectPropertyAxiom axiom =
				axiomFactory.getDHLTransitiveObjectPropertyAxiom(arg);
		
		additor.addAxiom(axiom);
		
	}
	
	// ABox

	@Override
	public void visit(SimpleClassAssertion arg) {
		
		DHLClassAssertion axiom =
				axiomFactory.getDHLClassAssertion(arg);
		
		additor.addAxiom(axiom);
		
	}

	@Override
	public void visit(SimpleObjectPropertyAssertion arg) {
		
		DHLObjectPropertyAssertion axiom =
				axiomFactory.getDHLObjectPropertyAssertion(arg);
		
		additor.addAxiom(axiom);
		
	}
	
	

}
