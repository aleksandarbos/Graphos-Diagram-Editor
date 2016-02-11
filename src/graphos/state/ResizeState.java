package graphos.state;

import graphos.commands.AddScaleElementCommand;
import graphos.gui.MainFrame;
import graphos.gui.workspace.DiagramGUI;
import graphos.gui.workspace.DiagramGUI.Handle;
import graphos.model.elements.DiagramDevice;
import graphos.model.elements.DiagramElement;
import graphos.model.workspace.Project;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

@SuppressWarnings("serial")
public class ResizeState extends State{
	
	Handle handleInMotion=null;
	private DiagramGUI diagram; 
	
	private int moveX;
	private int moveY;
	
	private DiagramDevice device;
	private double oldScale;
	
	
	public ResizeState(DiagramGUI md) {
		diagram = md;
	}
	
	
	public void mousePressed(MouseEvent e) {

	}
	
	public void mouseMoved(MouseEvent e) {
		//System.out.println("ooooooooooooooooooooooo");
	}	

	public void mouseDragged(MouseEvent e) {
		//System.out.println("ooooooooooooooooooooooo");
		if(diagram.getDiagram().getDiagramSelectionModel().getSelectionList().size() > 1)
			return;
		
		//uzmi taj jedini selektovani nad kime treba da pamtis akciju skaliranja
		device = (DiagramDevice) diagram.getDiagram().getDiagramSelectionModel().getSelectionList().get(0);
		
		Point2D position = e.getPoint();
		diagram.transformToUserSpace(position);
		if (handleInMotion==null){
			handleInMotion = diagram.getDeviceAndHandleForPoint(position);
			oldScale = device.getScale();		//stara vrednost pre trans
		}
		if (handleInMotion!=null){
		
			//device = (DiagramDevice) diagram.getDiagram().getDiagramSelectionModel().getSelectionList().get(0);
					
					switch(handleInMotion.ordinal()){
					//SE
					case 6:{	
						double razlikaX=position.getX()-(device.getPosition().getX()+device.getSize().getWidth());
						double razlikaY=position.getY()-(device.getPosition().getY()+device.getSize().getHeight());					
						System.out.println("x razlika: " + razlikaX + " , y razlika: " + razlikaY);
						double newWidth = device.getSize().getWidth()+razlikaX;
						double newHeight = device.getSize().getHeight()+razlikaY;
						double scaleX=newWidth/device.getInitSize().getWidth();
						double scaleY=newHeight/device.getInitSize().getHeight();
						double newScale = 1;
							if(scaleX<scaleY)
								newScale=scaleX;
							else
								newScale=scaleY;
						if(newScale<0.2)
							device.setScale(0.2);
						else if(newScale>3)
							device.setScale(3);
						else
							device.setScale(newScale);
						break;
					}
					//SW
					case 7:{	
					moveX=(int)(device.getPosition().getX()+device.getSize().getWidth());
					double razlikaX = device.getPosition().getX() - position.getX();
					double razlikaY = position.getY()- (device.getPosition().getY() + device.getSize().getHeight());
					double newWidth = device.getSize().getWidth() + razlikaX;
					double newHeight = device.getSize().getHeight() + razlikaY;
					double scaleX = newWidth / device.getInitSize().getWidth();
					double scaleY = newHeight / device.getInitSize().getHeight();
			
						double newScale = 1;
							if(scaleX>scaleY)
								newScale=scaleX;
							else
								newScale=scaleY;
						if(newScale<0.2)
							device.setScale(0.2);
						else if(newScale>3)
							device.setScale(3);
						else {
							device.setScale(newScale);
							
						}
						
						Point point = new Point((int) (moveX - device.getSize().getWidth()), (int) device.getPosition().getY());
						device.setPosition(point);
						
						break;
					}
					//NE	
					case 4: {
					moveY=(int)(device.getPosition().getY()+device.getSize().getHeight());
					double razlikaX = position.getX()- (device.getPosition().getX() + device.getSize().getWidth());
					double razlikaY = device.getPosition().getY() - position.getY();
					double newWidth = device.getSize().getWidth() + razlikaX;
					double newHeight = device.getSize().getHeight() + razlikaY;
					double scaleX = newWidth / device.getInitSize().getWidth();
					double scaleY = newHeight / device.getInitSize().getHeight();

						
						double newScale = 1;
						if(scaleX>scaleY)
							newScale=scaleX;
						else
							newScale=scaleY;
						if(newScale<0.2)
							device.setScale(0.2);
						else if(newScale>3)
							device.setScale(3);
						else
							device.setScale(newScale);
						
						Point point = new Point((int) device.getPosition().getX(),(int) (moveY - device.getSize().getHeight()));
						device.setPosition(point);
											
						break;
						}
					//NW
					case 5: {
						moveX=(int)(device.getPosition().getX()+device.getSize().getWidth());
						moveY=(int)(device.getPosition().getY()+device.getSize().getHeight());
						double razlikaX = device.getPosition().getX() - position.getX();
						double razlikaY = device.getPosition().getY() - position.getY();
						double newWidth = device.getSize().getWidth() + razlikaX;
						double newHeight = device.getSize().getHeight() + razlikaY;
						double scaleX = newWidth / device.getInitSize().getWidth();
						double scaleY = newHeight / device.getInitSize().getHeight();

							
							double newScale = 1;
							if(scaleX>scaleY)
								newScale=scaleX;
							else
								newScale=scaleY;
							if(newScale<0.2)
								device.setScale(0.2);
							else if(newScale>3)
								device.setScale(3);
							else
								device.setScale(newScale);
							
							Point point = new Point((int) (moveX - device.getSize().getWidth()),(int) (moveY - device.getSize().getHeight()));
							device.setPosition(point);
												
							break;
						
						}
					}
				diagram.updatePerformed(null);
				MainFrame.getInstance().getStatusBar().setElementLabel(device);
				((Project)MainFrame.getInstance().getTreePane().getCurrentProject()).updatePerformed(null);
				
		}
	}
	
	public void mouseReleased(MouseEvent e){
		System.out.println("ELEMENT : " + device.getName() + "SCALEeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee: " + device.getScale());
		diagram.getCommandManager().addCommand(
				new AddScaleElementCommand(diagram.getDiagram().getModel(),
									  diagram.getDiagram().getDiagramSelectionModel(), device.getScale(), oldScale));
		
		handleInMotion=null;
		diagram.startSelectState();
	}
}
