package random_csapatnev;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Swing Main Ablak
 *
 */
public class MainFrame extends JFrame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Singleton
	static MainFrame Instance; 
	
	Virologist v;
	int increment = 0;
	
	boolean aiToggle = true;
	
	JPanel balJp;
	JPanel jobbJp;
	
	JPanel jobbJpTop;
	JPanel jobbJpBot;
	
	JLabel actualField;
	JLabel actualMaterial;
	JLabel aminoacid;
	JLabel nucleotide;
	JLabel activeGears;
	JLabel passiveGears;
	JLabel activeAgents;
	JLabel knownAgents;
	JLabel craftedAgents;
	JLabel vitusEffect;
	JLabel paralyzedEffect;
	
	JButton jbtFieldInteraction;
	JButton jbtMove;
	JButton jbtStealMat;
	JButton jbtStealGear;
	JButton jbtGearInteraction;
	JButton jbtAdminBenit;
	JButton jbtAdminRound;
	JButton jbtCraftAgent;
	JButton jbtUseAgent;
	JButton jbtUseAxe;
	JButton jbtSave;
	JButton jbtAdminAddVirologist;
	JButton jbtAdminToggleThread;
	
	JFrame frame;
	View view;
	Model model;
	
	ArrayList<String> winCon = new ArrayList<>();
	
	public MainFrame(MainFrame _mf) {
		this();
		view = _mf.view;
		model = _mf.model;
		v = _mf.v;
		increment = _mf.increment;
		Instance = _mf.Instance;
		addPanels();
		refreshView();
		StartRounds();
	}
	
	public MainFrame() 
	{	
		Instance = this;
		frame = new JFrame("random_csapatnev main_frame");
		frame.setSize(1500, 1030);
		GridLayout layout = new GridLayout(1, 2);
		layout.setHgap(-500);
		frame.setLayout(layout);
		
		// Két Panel setuppolása
		balJp = new JPanel();
		jobbJp = new JPanel(new GridLayout(2, 1));
		jobbJpTop = new JPanel(new FlowLayout());
		jobbJpBot = new JPanel(new GridLayout(11,1));
		
		actualField = new JLabel("label1");
		actualMaterial = new JLabel("label2");
		aminoacid = new JLabel("label3");
		nucleotide = new JLabel("label4");
		activeGears = new JLabel("label5");
		passiveGears = new JLabel("label6");
		activeAgents = new JLabel("label7");
		knownAgents = new JLabel("label8");
		craftedAgents = new JLabel("label9");
		vitusEffect = new JLabel("label10");
		paralyzedEffect = new JLabel("label11");
		
		actualField.setFont(new Font("Arial", Font.BOLD, 15));
		actualMaterial.setFont(new Font("Arial", Font.BOLD, 15));
		aminoacid.setFont(new Font("Arial", Font.BOLD, 15));
		nucleotide.setFont(new Font("Arial", Font.BOLD, 15));
		activeGears.setFont(new Font("Arial", Font.BOLD, 15));
		passiveGears.setFont(new Font("Arial", Font.BOLD, 15));
		activeAgents.setFont(new Font("Arial", Font.BOLD, 15));
		knownAgents.setFont(new Font("Arial", Font.BOLD, 15));
		craftedAgents.setFont(new Font("Arial", Font.BOLD, 15));
		vitusEffect.setFont(new Font("Arial", Font.BOLD, 15));
		paralyzedEffect.setFont(new Font("Arial", Font.BOLD, 15));

		model = new Model();
		view = new View(model, frame);
		
		balJp.setLayout(null);
		
		balJp.setBackground(new Color(25, 25, 25));
		jobbJpTop.setBackground(new Color(50, 50, 50));
		jobbJpBot.setBackground(new Color(100, 100, 100));
		
		jbtFieldInteraction = new JButton(new AbstractAction("FieldInteract") {
			public void actionPerformed(ActionEvent ae) {
				v.FieldInteract();
				refreshView();
			}
		});
		
		jbtMove = new JButton(new AbstractAction("Move") {
			public void actionPerformed(ActionEvent ae) {
				boolean top = false, right = false, left = false, bot = false;
				int x = v.currField.x;
				int y = v.currField.y;
				
				if(x-1 >= 0 && model.fields[x-1][y] != null) {
					top = true;
				}
				if(y-1 >= 0 && model.fields[x][y-1] != null) {
					left = true;
				}
				if(x+1 <= model.M-1 && model.fields[x+1][y] != null) {
					bot = true;
				}
				if(y+1 <= model.N-1 && model.fields[x][y+1] != null) {
					right = true;
				}
				MerreFrame(top, right, bot, left);
			}
		});
		
		jbtStealMat = new JButton(new AbstractAction("Anyag lopása másik virológustól.") {
			public void actionPerformed(ActionEvent ae) {
				try {
					if(v.currField.characters.size() > 1) {
						VirologistFrame(v.currField.characters, "stealMaterial");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		jbtStealGear = new JButton(new AbstractAction("Felszerelés lopása másik virológustól.") {
			public void actionPerformed(ActionEvent ae) {
				try {
					if(v.currField.characters.size() > 1) {
						VirologistFrame(v.currField.characters, "stealGear");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		jbtGearInteraction = new JButton(new AbstractAction("Felszerelés Interakciók") {
			public void actionPerformed(ActionEvent ae) {
				if(v.gears.size() > 0 || v.activeGears.size() > 0) {
					GearPickerFrame();
				}
			}
		});
		
		jbtCraftAgent = new JButton(new AbstractAction("Craft ágens") {
			public void actionPerformed(ActionEvent ae) {
				AgentCraftPickerFrame();
			}
		});
		
		jbtUseAgent = new JButton(new AbstractAction("Ágens használata") {
			public void actionPerformed(ActionEvent ae) {
				try {
					VirologistFrame(v.currField.characters, "useAgent");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		jbtUseAxe = new JButton(new AbstractAction("Balta használata") {
			public void actionPerformed(ActionEvent ae) {
				try {
					if(v.currField.characters.size() > 1) {
						VirologistFrame(v.currField.characters, "useAxe");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		jbtAdminBenit = new JButton(new AbstractAction("Admin: Character bénítása") {
			public void actionPerformed(ActionEvent ae) {
				try {
					if(v.currField.characters.size() > 1) {
						VirologistFrame(v.currField.characters, "benit");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		jbtAdminRound = new JButton(new AbstractAction("Admin: Kör léptetése") {
			public void actionPerformed(ActionEvent ae) {
				Round();
				AI_Round();
				refreshView();
			}
		});
		
		jbtAdminAddVirologist = new JButton(new AbstractAction("Admin: Virológus lerakása") {
			public void actionPerformed(ActionEvent ae) {
				Virologist vEnemy = new Virologist("v" + increment++);
				vEnemy.currMaterial.AddMaterial(new Material(50, 50));
				model.characters.add(vEnemy);
				vEnemy.currField = v.currField;
				vEnemy.currField.characters.add(vEnemy);
				GraphicsVirologist vGEnemy = new GraphicsVirologist(vEnemy);
				model.graphicsCharacter.add(vGEnemy);
			}
		});
		
		jbtAdminToggleThread = new JButton(new AbstractAction("Admin: AI Thread megállítása/elindítása") {
			public void actionPerformed(ActionEvent ae) {
				aiToggle = !aiToggle;
			}
		});
		
		jobbJpTop.setBorder(BorderFactory.createEmptyBorder(0, 500, 0, 0));
		jobbJpBot.setBorder(BorderFactory.createEmptyBorder(0, 500, 0, 0));
		
		jobbJpTop.add(jbtFieldInteraction);
		jobbJpTop.add(jbtMove);
		jobbJpTop.add(jbtStealMat);
		jobbJpTop.add(jbtStealGear);
		jobbJpTop.add(jbtGearInteraction);
		jobbJpTop.add(jbtCraftAgent);
		jobbJpTop.add(jbtUseAgent);
		jobbJpTop.add(jbtUseAxe);
		jobbJpTop.add(jbtAdminBenit);
		jobbJpTop.add(jbtAdminRound);
		jobbJpTop.add(jbtAdminAddVirologist);
		jobbJpTop.add(jbtAdminToggleThread);
		//jobbJpTop.add(jbtSave);
		
		jobbJpBot.add(actualField);
		jobbJpBot.add(actualMaterial);
		jobbJpBot.add(aminoacid);
		jobbJpBot.add(nucleotide);
		jobbJpBot.add(activeGears);
		jobbJpBot.add(passiveGears);
		jobbJpBot.add(activeAgents);
		jobbJpBot.add(knownAgents);
		jobbJpBot.add(craftedAgents);
		jobbJpBot.add(vitusEffect);
		jobbJpBot.add(paralyzedEffect);
		
		jobbJp.add(jobbJpTop);
		jobbJp.add(jobbJpBot);
		
		frame.add(balJp);
		frame.add(jobbJp);
		
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setResizable(true);
        frame.setVisible(true);
	}
	public MainFrame(boolean random, String mapMatrix, int enemyCount)
	{
		this();
		if(random) {
			generateRandomFields();
		}
		else {
			generateFieldsFromMapMatrix(mapMatrix);
		}
		addPanels();
		
		v = new Virologist("v" + increment++);
		model.characters.add(v);
		v.currField = model.fields[0][0];
		model.fields[0][0].characters.add(v);
		GraphicsVirologist vG = new GraphicsVirologist(v);
	    int pLen = 750 / model.graphicsFields.length;
		model.graphicsCharacter.add(vG);
		
		for(int i = 0; i < enemyCount; i++) {
			Virologist vEnemy = new Virologist("v" + increment++);
			vEnemy.currMaterial.AddMaterial(new Material(50, 50));
			model.characters.add(vEnemy);
			int randN = new Random().nextInt(model.N);
			int randM = new Random().nextInt(model.M);
			vEnemy.currField = model.fields[randN][randM];
			model.fields[randN][randM].characters.add(vEnemy);
			GraphicsVirologist vGEnemy = new GraphicsVirologist(vEnemy);
			model.graphicsCharacter.add(vGEnemy);
		}
		refreshView();
		StartRounds();
	}
	
	public void StartRounds() {
		Thread t1 = null;
		t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {
						Thread.sleep(3000);
						if(aiToggle) {
							OverallRound();
							refreshView();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
	}
	
	private void generateFieldsFromMapMatrix(String inputString)
	{		
		String[] rows = inputString.split(";");
		model.N = rows.length;
		model.M = rows[0].length();
	    model.graphicsFields = new GraphicsFieldBase[rows[0].length()][rows.length];
	    model.fields = new Field[rows[0].length()][rows.length];
	    for(int i = 0; i < model.graphicsFields.length; ++i)
	    {
	        for(int j = 0; j < model.graphicsFields[i].length; ++j)
	        {
	        	if(i < rows.length && j < rows[j].length())
	        	{
		            switch(rows[i].charAt(j))
		            {
		                case 'W':
		                	Warehouse wh = new Warehouse(i, j);
		                	model.fields[i][j] = wh;
		                	model.graphicsFields[i][j] = new GraphicsWarehouse(i, j);
		                	GraphicsMaterial gm = new GraphicsMaterial(wh.material, model.graphicsFields[i][j]);
		                	model.graphicsMaterial.add(gm);
		                    break;
		                case 'L':
		                	model.fields[i][j] = new Laboratory(i, j);
		                	model.graphicsFields[i][j] = new GraphicsLaboratory(i, j);
		                    break;
		                case 'S':
		                	Safehouse sf = new Safehouse(i,j);
		                	model.fields[i][j] = sf;
		                	model.graphicsFields[i][j] = new GraphicsSafehouse(i, j);
		                	switch(sf.gear.name)
		                	{
		                		case CLOAK:
		                			model.graphicsGear.add(new GraphicsCloak(sf, model.graphicsFields[i][j]));
		                			break;
		                		case GLOVES:
		                			model.graphicsGear.add(new GraphicsGloves(sf, model.graphicsFields[i][j]));
		                			break;
		                		case SACK:
		                			model.graphicsGear.add(new GraphicsSack(sf, model.graphicsFields[i][j]));
		                			break;
		                		case AXE:
		                			model.graphicsGear.add(new GraphicsAxe(sf, model.graphicsFields[i][j]));
		                			break;
		                	}
		                    break;
		                case 'F':
		                	model.fields[i][j] = new Field(i, j);
		                	model.graphicsFields[i][j] = new GraphicsField(i, j);
		                    break;
		                default:
		                	model.fields[i][j] = new Field(i, j);
		                	model.graphicsFields[i][j] = new GraphicsField(i, j);
		                    break;
		            }
	        	}
	        }
	    }
	    for(int i = 0; i < model.fields.length; ++i)
	    {
	        for(int j = 0; j < model.fields[i].length; ++j)
	        {
	        	if(model.fields[i][j] != null)
	        	{
	        		if(i < model.fields.length - 1)
	        		{
	        			if(model.fields[i+1][j] != null)
	        			{
		        			model.fields[i][j].neighbours.add(model.fields[i+1][j]);	
	        			}
	        		}
	        		if(i > 0)
	        		{
	        			if(model.fields[i-1][j] != null)
	        			{
		        			model.fields[i][j].neighbours.add(model.fields[i-1][j]);	
	        			}
	        		}
	        		if(j > 0)
	        		{
	        			if(model.fields[i][j-1] != null)
	        			{
		        			model.fields[i][j].neighbours.add(model.fields[i][j-1]);	
	        			}	
	        		}
	        		if(j < model.fields[i].length - 1)
	        		{
	        			if(model.fields[i][j+1] != null)
	        			{
		        			model.fields[i][j].neighbours.add(model.fields[i][j+1]);	
	        			}	
	        		}
	        	}
	        }
	    }
	}
	private void generateRandomFields()
	{
		Random rand = new Random();
		int length = rand.nextInt(15 - 10) + 10;
	    String inputStr = "";
	    for(int i = 0; i < length; ++i)
	    {
	    	for(int j = 0; j < length; ++j)
	    	{
	    		int random = rand.nextInt(100);
	    		if(random < 3)
	    		{
    				inputStr += "S";
	    		}
	    		else if(random >= 3 && random < 6)
	    		{
    				inputStr += "W";
	    		}
	    		else if(random >= 6 && random < 11)
	    		{
    				inputStr += "L";	
	    		}
	    		else
	    		{
    				inputStr += "F";
	    		}
	    	}
	    	inputStr += ";";
	    }
	    generateFieldsFromMapMatrix(inputStr);
	}
	private void addPanels()
	{
	    int pLen = 1000 / model.graphicsFields.length;
	    for(int i = 0; i < model.graphicsFields.length; ++i)
        {
            for(int j = 0; j < model.graphicsFields[i].length; ++j)
            {
                if(model.graphicsFields[i][j] != null)
                {
                	balJp.add(model.graphicsFields[i][j]);
                	model.graphicsFields[i][j].setBounds(pLen*j, pLen*i, pLen, pLen);
                }
            }
        }
	    refreshView();
	}
	// Ide tartozik a HUD többi elemének rajzolása/kezelése is
	public void refreshView() 
	{
		view.repaintAll();
		if(v != null) {
			UpdateLabels();
		}
		this.invalidate();
		this.repaint();
	}
	
    public void VirologistFrame(final ArrayList<Character> virList, final String action) throws InterruptedException {
        final JFrame jf = new JFrame("random_csapatnev virologist_frame");
        jf.setSize(250, 350);
        jf.setLayout(new FlowLayout());
        
        ArrayList<JButton> buttList = new ArrayList<>();
        
        for(Character s : virList) {
            JButton item = new JButton(s.name);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Character c = null;
                	for(Character ve : virList) {
                		if(ve.name.equals(e.getActionCommand())){
                			c = ve;
                		}
                	}
                	if(action == "useAgent") {
                		AgentPickerFrame(c);
                	}
                	else if (action == "stealMaterial") {
                		System.out.println(c);
                		v.StealMaterialInteract(c);
                	}
                	else if (action == "stealGear") {
                		v.StealGearInteract(c);
                	}
                	else if (action == "benit") {
                		c.SetIsParalyzed(true);
                	}
                	else if (action == "useAxe") {
                		if(c.name.startsWith("b")) {
                			for(Gear g : v.activeGears) {
                    			if (g.name == GearEnum.AXE) {
                    				if(g.canUse) {
                    					g.Effect(c);
                    				}
                    			}
                    		}
                		}
                	}
                	refreshView();
                	jf.dispose();
                }
            });
            item.setFont(new Font("Arial", Font.BOLD, 25));
            jf.add(item);
            buttList.add(item);
        }
        
        jf.setResizable(false);
        jf.setLocationRelativeTo(frame);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true); 
    }
    
    public void AgentPickerFrame(final Character c) {
        final JFrame jf = new JFrame("random_csapatnev agent_picker_frame");
        jf.setSize(250, 350);
        jf.setLayout(new FlowLayout());
        
        ArrayList<JButton> buttList = new ArrayList<>();
        
        for(Agent s : v.craftedAgents) {
            JButton item = new JButton(s.name);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Agent a = null;
                	for(Agent agent : v.craftedAgents) {
                		if(agent.name.equals(e.getActionCommand())) {
                			a = agent;
                		}
                	}
                	if(a != null) {
                		v.Use(c, a);
                	}
                	refreshView();
                	jf.dispose();
                }
            });
            item.setFont(new Font("Arial", Font.BOLD, 25));
            jf.add(item);
            buttList.add(item);
        }
        
        jf.setResizable(false);
        jf.setLocationRelativeTo(frame);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true); 
    }
    
    public void AgentCraftPickerFrame() {
        final JFrame jf = new JFrame("random_csapatnev agent_craft_picker_frame");
        jf.setSize(250, 350);
        jf.setLayout(new FlowLayout());
        
        ArrayList<JButton> buttList = new ArrayList<>();
        
        for(Agent s : v.knownAgents) {
            JButton item = new JButton(s.name);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Agent a = null;
                	for(Agent agent : v.knownAgents) {
                		if(agent.name.equals(e.getActionCommand())) {
                			a = agent;
                		}
                	}
                	if(a != null) {
                		v.CraftAgent(a);
                	}
                	refreshView();
                	jf.dispose();
                }
            });
            item.setFont(new Font("Arial", Font.BOLD, 25));
            jf.add(item);
            buttList.add(item);
        }
        
        jf.setResizable(false);
        jf.setLocationRelativeTo(frame);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true); 
    }
    
    public void GearPickerFrame() {
        final JFrame jf = new JFrame("random_csapatnev gear_picker_frame");
        jf.setSize(250, 350);
        jf.setLayout(new FlowLayout());
        
        ArrayList<JButton> buttList = new ArrayList<>();
        
        for(Gear s : v.gears) {
            JButton item = new JButton(s.name.toString());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Gear g = null;
                	for(Gear ge : v.gears) {
                		if(ge.name.equals(StringToGearEnum(e.getActionCommand()))) {
                			g = ge;
                		}
                	}
                	if(g != null) {
                		v.EquipGear(g.name);
                	}
                	refreshView();
                	jf.dispose();
                }
            });
            item.setFont(new Font("Arial", Font.BOLD, 25));
            jf.add(item);
            buttList.add(item);
        }
        
        for(Gear s : v.activeGears) {
            JButton item = new JButton(s.name.toString());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Gear g = null;
                	for(Gear ge : v.activeGears) {
                		if(ge.name.equals(StringToGearEnum(e.getActionCommand()))) {
                			g = ge;
                		}
                	}
                	if(g != null) {
                		v.UnequipGear(g.name);
                	}
                	refreshView();
                	jf.dispose();
                }
            });
            item.setFont(new Font("Arial", Font.BOLD, 25));
            item.setForeground(Color.RED);
            jf.add(item);
            buttList.add(item);
        }
        
        jf.setResizable(false);
        jf.setLocationRelativeTo(frame);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true); 
    }
	
    public GearEnum StringToGearEnum(String s) {
    	switch(s) {
    	case "AXE":
    		return GearEnum.AXE;
    	case "CLOAK":
    		return GearEnum.CLOAK;
    	case "SACK":
    		return GearEnum.SACK;
    	case "GLOVES":
    		return GearEnum.GLOVES;
    	default:
    		return null;
    	}
    }
    
	public void MerreFrame(boolean fel, boolean jobb, boolean le, boolean bal) {
        final JFrame jf = new JFrame("random_csapatnev merre_frame");
        jf.setSize(200, 250);
        jf.setLayout(new BorderLayout());
        
        JButton jbt_fel = new JButton(new AbstractAction("↑") {
            public void actionPerformed(ActionEvent ae) {
            	v.Move(model.fields[v.currField.x - 1][v.currField.y]);
            	refreshView();
            	jf.dispose();
            }
        });
        
        JButton jbt_jobb = new JButton(new AbstractAction("→") {
            public void actionPerformed(ActionEvent ae) {
            	v.Move(model.fields[v.currField.x][v.currField.y + 1]);            	
            	refreshView();
            	jf.dispose();
            }
        });
        
        JButton jbt_le = new JButton(new AbstractAction("↓") {

            public void actionPerformed(ActionEvent ae) {
            	v.Move(model.fields[v.currField.x + 1][v.currField.y]);
            	refreshView();
            	jf.dispose();
            }
        });
        
        JButton jbt_bal = new JButton(new AbstractAction("←") {
            public void actionPerformed(ActionEvent ae) 
            {
                v.Move(model.fields[v.currField.x][v.currField.y - 1]);
                
                refreshView();
                jf.dispose();
            }
        });
        
        jbt_fel.setFont(new Font("Arial", Font.BOLD, 25));
        jbt_jobb.setFont(new Font("Arial", Font.BOLD, 25));
        jbt_le.setFont(new Font("Arial", Font.BOLD, 25));
        jbt_bal.setFont(new Font("Arial", Font.BOLD, 25));
        
        jbt_fel.setEnabled(fel);
        jbt_jobb.setEnabled(jobb);
        jbt_le.setEnabled(le);
        jbt_bal.setEnabled(bal);
        
        jf.add(jbt_fel, BorderLayout.PAGE_START);
        jf.add(jbt_jobb, BorderLayout.LINE_END);
        jf.add(jbt_le, BorderLayout.PAGE_END);
        jf.add(jbt_bal, BorderLayout.LINE_START);
        
        jf.setResizable(false);
        jf.setLocationRelativeTo(jbtMove);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true); 
    }
	
	public void UpdateLabels() {
		actualField.setText(String.format("Aktuális Mező: (%s;%s)", v.currField.x, v.currField.y));
		
		actualMaterial.setText("Aktuális Anyagok:");
		aminoacid.setText(String.format("    -Aminosav: %s/%s", v.currMaterial.container.get(MatEnum.AMINOACID), v.maxMaterial.container.get(MatEnum.AMINOACID)));
		nucleotide.setText(String.format("    -Nukleotid: %s/%s", v.currMaterial.container.get(MatEnum.NUCLEOTIDE), v.maxMaterial.container.get(MatEnum.NUCLEOTIDE)));

		String activeGearsS = "Aktív felszerelések: ";
		for(Gear a : v.activeGears) {
			if(a.name == GearEnum.AXE) 
			{
				if(a.canUse) {
					activeGearsS += a.name + ", ";
				}
				else {
					activeGearsS += a.name + "(B), ";
				}
			}
			else 
			{
				activeGearsS += a.name + ", ";
			}
			
		}
		activeGears.setText(activeGearsS.substring(0, activeGearsS.length()-2));
		
		String passiveGearsS = "Inaktív felszerelések: ";
		for(Gear a : v.gears) {
			passiveGearsS += a.name + ", ";
		}
		passiveGears.setText(passiveGearsS.substring(0, passiveGearsS.length()-2));
		
		String activeAgentsS = "Aktív ágensek: ";
		for(Agent a : v.activeAgents) {
			activeAgentsS += a.name + ", ";
		}
		activeAgents.setText(activeAgentsS.substring(0, activeAgentsS.length()-2));
		
		String knownAgentsS = "Megtanult ágensek: ";
		for(Agent a : v.knownAgents) {
			knownAgentsS += a.name + ", ";
		}
		knownAgents.setText(knownAgentsS.substring(0, knownAgentsS.length()-2));
		
		String craftedAgentsS = "Craftolt ágensek: ";
		for(Agent a : v.craftedAgents) {
			craftedAgentsS += a.name + ", ";
		}
		craftedAgents.setText(craftedAgentsS.substring(0, craftedAgentsS.length()-2));
		
		vitusEffect.setText(String.format("Vitustánc hatás?: %s", v.isVitus));
		paralyzedEffect.setText(String.format("Bénult hatás?: %s", v.isParalyzed));
	}
	
	public void OverallRound() {
		Round();
		AI_Round();
	}

	public void Round()
	{
		ArrayList<Character> tempList = new ArrayList<Character>(model.characters.size());
		for(Character c : model.characters) {
			tempList.add(c);
		}
		for(Character c : tempList) {
			c.Round();
		}
	}
	
	public void AI_Round() {
		ArrayList<Character> tempList = new ArrayList<Character>(model.characters.size());
		for(Character c : model.characters) {
			tempList.add(c);
		}
		for(Character c : tempList) {
			if(!c.name.equals("v0")) {
				ArrayList<Field> neighFields = c.currField.GetNeighbours();
				Random r = new Random();
				int rand = r.nextInt(neighFields.size());
				c.Move(neighFields.get(rand));
				c.FieldInteract();
			}
		}
	}
	
	public void EndCheck()
	{
		for(Character c : model.characters) {
			if(c.knownAgents.size() == winCon.size()) {
				if(c.name.equals("v0")) {
					Main.endGame(true);
				}
				else {
					Main.endGame(false);
				}
			}
		}
	}
}