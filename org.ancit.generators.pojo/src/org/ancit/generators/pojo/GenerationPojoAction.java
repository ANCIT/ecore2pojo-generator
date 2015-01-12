package org.ancit.generators.pojo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.ancit.generators.pojo.templates.PojoClassGeneratorTemplate;
import org.ancit.generators.pojo.templates.PojoEnumGeneratorTemplate;
import org.ancit.generators.pojo.templates.PojoInterfaceTemplate;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.EEnumImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class GenerationPojoAction implements IObjectActionDelegate {

	private IFile ecoreFile;

	public GenerationPojoAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(IAction action) {
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
				System.out.println(eClassifier);
				if (eClassifier instanceof EClass) {
					EClass eClass = ((EClass) eClassifier);
					if (eClass.isInterface()) {
						PojoInterfaceTemplate generateInterfaceTemplate = new PojoInterfaceTemplate();
						// if (eClassifier instanceof EClass) {
						String generatedClass = generateInterfaceTemplate
								.generate(eClassifier);

						// convert String into InputStream
						InputStream is = new ByteArrayInputStream(
								generatedClass.getBytes());

						IContainer parent = ecoreFile.getParent();
						IFile file = null;
						if (parent instanceof IFolder) {
							file = ((IFolder) parent).getFile(eClassifier
									.getName() + ".java");
						} else {
							file = ((IProject) parent).getFile(eClassifier
									.getName() + ".java");
						}
						if (file.exists()) {
							file.delete(0, null);
						}
						file.create(is, true, null);

						// eClass = ((EClass) eClassifier);
						// EList<EClass> eSuperTypes = eClass.getESuperTypes();
						// String superTypes = new String();
						// for (EClass sClass : eSuperTypes) {
						// if (eSuperTypes.indexOf(sClass) + 1 == eSuperTypes
						// .size()) {
						// superTypes += sClass.getName();
						// } else {
						// superTypes += sClass.getName() + ",";
						// }
						// }
						// }
					} else {
						PojoClassGeneratorTemplate generatorTemplate = new PojoClassGeneratorTemplate();
						// if (eClassifier instanceof EClass) {
						String generatedClass = generatorTemplate
								.generate(eClassifier);

						// convert String into InputStream
						InputStream is = new ByteArrayInputStream(
								generatedClass.getBytes());

						IContainer parent = ecoreFile.getParent();
						IFile file = null;
						if (parent instanceof IFolder) {
							file = ((IFolder) parent).getFile(eClassifier
									.getName() + ".java");
						} else {
							file = ((IProject) parent).getFile(eClassifier
									.getName() + ".java");
						}
						if (file.exists()) {
							file.delete(0, null);
						}
						file.create(is, true, null);

						eClass = ((EClass) eClassifier);
						EList<EClass> eSuperTypes = eClass.getESuperTypes();
						String superTypes = new String();
						String implTypes = new String();
						for (EClass sClass : eSuperTypes) {
							if (sClass.isInterface()) {

								if (eSuperTypes.indexOf(sClass) + 1 == eSuperTypes
										.size()) {
									implTypes += sClass.getName();
								} else {
									implTypes += sClass.getName() + ",";
								}

							} else {
								if (eSuperTypes.indexOf(sClass) + 1 == eSuperTypes
										.size()) {
									superTypes += sClass.getName();
								} else {
									superTypes += sClass.getName() + ",";
								}
							}
						}
						EList<EStructuralFeature> eStructuralFeatures = eClass
								.getEStructuralFeatures();
						for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {

							if (eStructuralFeature instanceof EReferenceImpl) {
								System.out
										.println(eStructuralFeature.getName());
								System.out.println(eStructuralFeature
										.getEGenericType().getERawType()
										.getName());
								if (eStructuralFeature.getUpperBound() < 0
										|| eStructuralFeature.getUpperBound() > 1) {

								}
							} else if (eStructuralFeature instanceof EAttributeImpl) {
								System.out
										.println(eStructuralFeature.getName());
								System.out.println(eStructuralFeature
										.getEType().getInstanceClassName());

							}
							System.out.println("-----------");
						}
						// EList<EAttribute> eAttributes =
						// eClass.getEAttributes();
						// for (EAttribute eAttribute : eAttributes) {
						// // System.out.println(eAttribute.getName());
						// // System.out.println(eAttribute.getEType()
						// // .getInstanceClassName());
						// eAttribute
						// .getEType()
						// .getInstanceClassName()
						// .substring(
						// eAttribute.getEType()
						// .getInstanceClassName()
						// .lastIndexOf(".") + 1);
						// }

						// }

					}
				}
				if (eClassifier instanceof EEnumImpl) {
					EEnumImpl eClass = ((EEnumImpl) eClassifier);
//					if (eClass.isInterface()) {
					PojoEnumGeneratorTemplate generateEnumTemplate = new PojoEnumGeneratorTemplate();
						// if (eClassifier instanceof EClass) {
						String generatedClass = generateEnumTemplate
								.generate(eClassifier);
						
						// convert String into InputStream
						InputStream is = new ByteArrayInputStream(
								generatedClass.getBytes());

						IContainer parent = ecoreFile.getParent();
						IFile file = null;
						if (parent instanceof IFolder) {
							file = ((IFolder) parent).getFile(eClassifier
									.getName() + ".java");
						} else {
							file = ((IProject) parent).getFile(eClassifier
									.getName() + ".java");
						}
						if (file.exists()) {
							file.delete(0, null);
						}
						file.create(is, true, null);

						// eClass = ((EClass) eClassifier);
						// EList<EClass> eSuperTypes = eClass.getESuperTypes();
						// String superTypes = new String();
						// for (EClass sClass : eSuperTypes) {
						// if (eSuperTypes.indexOf(sClass) + 1 == eSuperTypes
						// .size()) {
						// superTypes += sClass.getName();
						// } else {
						// superTypes += sClass.getName() + ",";
						// }
						// }
						// }
//					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteFileExist(IFile file) {
		System.out.println((File) file.getLocation());

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sSelection = (IStructuredSelection) selection;
			Object firstElement = sSelection.getFirstElement();
			if (firstElement instanceof IFile) {
				ecoreFile = (IFile) firstElement;
				System.out.println(ecoreFile.getName());
			}
		}

	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}

}
