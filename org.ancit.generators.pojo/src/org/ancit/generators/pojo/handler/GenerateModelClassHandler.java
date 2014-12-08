package org.ancit.generators.pojo.handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.ancit.generators.pojo.templates.PojoGeneratorTemplate;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class GenerateModelClassHandler extends AbstractHandler {
	private IFile ecoreFile;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ResourceSet rSet = new ResourceSetImpl();
		rSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("ecore", new EcoreResourceFactoryImpl());

		Resource ecoreResource = rSet.getResource(
				URI.createFileURI(ecoreFile.getLocation().toOSString()), true);
		try {
			ecoreResource.load(null);
			EPackage ecorePackage = (EPackage) ecoreResource.getContents().get(
					0);

			EList<EClassifier> eClassifiers = ecorePackage.getEClassifiers();
			for (EClassifier eClassifier : eClassifiers) {
				PojoGeneratorTemplate generatorTemplate = new PojoGeneratorTemplate();
				if (eClassifier instanceof EClass) {
					String generatedClass = generatorTemplate
							.generate(eClassifier);

					// convert String into InputStream
					InputStream is = new ByteArrayInputStream(
							generatedClass.getBytes());

					IContainer parent = ecoreFile.getParent();
					IFile file = null;
					if (parent instanceof IFolder) {
						file = ((IFolder) parent).getFile(eClassifier.getName()
								+ ".java");
					} else {
						file = ((IProject) parent).getFile(eClassifier
								.getName() + ".java");
					}
					file.create(is, true, null);

					EClass eClass = ((EClass) eClassifier);
					EList<EClass> eSuperTypes = eClass.getESuperTypes();
					String superTypes = new String();
					for (EClass sClass : eSuperTypes) {
						if (eSuperTypes.indexOf(sClass) + 1 == eSuperTypes
								.size()) {
							superTypes += sClass.getName();
						} else {
							superTypes += sClass.getName() + ",";
						}
					}
					EList<EAttribute> eAttributes = eClass.getEAttributes();
					for (EAttribute eAttribute : eAttributes) {
						System.out.println(eAttribute.getName());
						System.out.println(eAttribute.getEType()
								.getInstanceClassName());
					}

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
