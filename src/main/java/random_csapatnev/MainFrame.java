package random_csapatnev;

import java.util.ArrayList;
import java.util.List;
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
public class MainFrame extends JFrame {
	Random rand = new Random();
	boolean gameRunning = true;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Singleton
	static MainFrame Instance = null;

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

	public MainFrame(MainFrame inputMF) {
		this();
		view = inputMF.view;
		model = inputMF.model;
		v = inputMF.v;
		increment = inputMF.increment;
		addPanels();
		refreshView();
		startRounds();
	}

	public MainFrame() {
		MainFrame.Instance = this;
		frame = new JFrame("random_csapatnev main_frame");
		frame.setSize(1500, 1030);
		GridLayout layout = new GridLayout(1, 2);
		layout.setHgap(-500);
		frame.setLayout(layout);

		// Két Panel setuppolása
		balJp = new JPanel();
		jobbJp = new JPanel(new GridLayout(2, 1));
		jobbJpTop = new JPanel(new FlowLayout());
		jobbJpBot = new JPanel(new GridLayout(11, 1));

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

		actualField.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		actualMaterial.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		aminoacid.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		nucleotide.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		activeGears.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		passiveGears.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		activeAgents.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		knownAgents.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		craftedAgents.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		vitusEffect.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));
		paralyzedEffect.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 15));

		model = new Model();
		view = new View(model, frame);

		balJp.setLayout(null);

		balJp.setBackground(new Color(25, 25, 25));
		jobbJpTop.setBackground(new Color(50, 50, 50));
		jobbJpBot.setBackground(new Color(100, 100, 100));

		jbtFieldInteraction = new JButton(new AbstractAction("FieldInteract") {
			public void actionPerformed(ActionEvent ae) {
				v.fieldInteract();
				refreshView();
			}
		});

		jbtMove = new JButton(new AbstractAction("Move") {
			public void actionPerformed(ActionEvent ae) {
				boolean top = false;
				boolean right = false;
				boolean left = false;
				boolean bottom = false;
				int x = v.currField.x;
				int y = v.currField.y;

				top = isValidField(x - 1, y, model);
				left = isValidField(x, y - 1, model);
				bottom = isValidField(x + 1, y, model);
				right = isValidField(x, y + 1, model);

				movePickerFrame(top, right, bottom, left);
			}
		});

		jbtStealMat = new JButton(new AbstractAction("Anyag lopása másik virológustól.") {
			public void actionPerformed(ActionEvent ae) {
				if (v.currField.getCharacters().size() > 1) {
					virologistFrame(v.currField.getCharacters(), "stealMaterial");
				}
			}
		});

		jbtStealGear = new JButton(new AbstractAction("Felszerelés lopása másik virológustól.") {
			public void actionPerformed(ActionEvent ae) {
				if (v.currField.getCharacters().size() > 1) {
					virologistFrame(v.currField.getCharacters(), "stealGear");
				}
			}
		});

		jbtGearInteraction = new JButton(new AbstractAction("Felszerelés Interakciók") {
			public void actionPerformed(ActionEvent ae) {
				if (!v.gears.isEmpty() || !v.activeGears.isEmpty()) {
					gearPickerFrame();
				}
			}
		});

		jbtCraftAgent = new JButton(new AbstractAction("Craft ágens") {
			public void actionPerformed(ActionEvent ae) {
				agentCraftPickerFrame();
			}
		});

		jbtUseAgent = new JButton(new AbstractAction("Ágens használata") {
			public void actionPerformed(ActionEvent ae) {
				virologistFrame(v.currField.getCharacters(), "useAgent");
			}
		});

		jbtUseAxe = new JButton(new AbstractAction("Balta használata") {
			public void actionPerformed(ActionEvent ae) {
				if (v.currField.getCharacters().size() > 1) {
					virologistFrame(v.currField.getCharacters(), "useAxe");
				}
			}
		});

		jbtAdminBenit = new JButton(new AbstractAction("Admin: Character bénítása") {
			public void actionPerformed(ActionEvent ae) {
				if (v.currField.getCharacters().size() > 1) {
					virologistFrame(v.currField.getCharacters(), "benit");
				}
			}
		});

		jbtAdminRound = new JButton(new AbstractAction("Admin: Kör léptetése") {
			public void actionPerformed(ActionEvent ae) {
				round();
				aiRound();
				refreshView();
			}
		});

		jbtAdminAddVirologist = new JButton(new AbstractAction("Admin: Virológus lerakása") {
			public void actionPerformed(ActionEvent ae) {
				Virologist vEnemy = new Virologist("v" + increment++);
				vEnemy.currMaterial.addMaterial(new Material(50, 50));
				model.getCharacters().add(vEnemy);
				vEnemy.currField = v.currField;
				vEnemy.currField.getCharacters().add(vEnemy);
				GraphicsVirologist vGEnemy = new GraphicsVirologist(vEnemy);
				model.getGraphicsCharacter().add(vGEnemy);
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
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		frame.setResizable(true);
		frame.setVisible(true);
	}

	private boolean isValidField(int inputX, int inputY, Model inputModel) {
		return inputX >= 0 && inputX < inputModel.sizeM && inputY >= 0 && inputY < inputModel.sizeN
				&& inputModel.fields[inputX][inputY] != null;
	}

	public MainFrame(boolean random, String mapMatrix, int enemyCount) {
		this();
		if (random) {
			generateRandomFields();
		} else {
			generateFieldsFromMapMatrix(mapMatrix);
		}
		addPanels();

		v = new Virologist("v" + increment++);
		model.getCharacters().add(v);
		v.currField = model.fields[0][0];
		model.fields[0][0].getCharacters().add(v);
		GraphicsVirologist vG = new GraphicsVirologist(v);
		model.getGraphicsCharacter().add(vG);

		for (int i = 0; i < enemyCount; i++) {
			Virologist vEnemy = new Virologist("v" + increment++);
			vEnemy.currMaterial.addMaterial(new Material(50, 50));
			model.getCharacters().add(vEnemy);
			int randN = new Random().nextInt(model.sizeN);
			int randM = new Random().nextInt(model.sizeM);
			vEnemy.currField = model.fields[randN][randM];
			model.fields[randN][randM].getCharacters().add(vEnemy);
			GraphicsVirologist vGEnemy = new GraphicsVirologist(vEnemy);
			model.getGraphicsCharacter().add(vGEnemy);
		}
		refreshView();
		startRounds();
	}

	public void startRounds() {
		Thread t1 = null;
		t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (gameRunning) {
						Thread.sleep(3000);
						if (aiToggle) {
							overallRound();
							refreshView();
						}
					}
				} catch (InterruptedException e) {
					Logger.out(java.util.logging.Level.SEVERE, e.getMessage());
					Thread.currentThread().interrupt();
				}
			}
		});
		t1.start();
	}

	private void generateFieldsFromMapMatrix(String inputString) {
		String[] rows = inputString.split(";");
		initializeModelSize(rows);
		initializeGraphicsFields(rows);
		initializeFields();
		connectNeighbours();
	}

	private void initializeModelSize(String[] inputRows) {
		model.sizeN = inputRows.length;
		model.sizeM = inputRows[0].length();
	}

	private void initializeGraphicsFields(String[] rows) {
		model.graphicsFields = new GraphicsFieldBase[rows[0].length()][rows.length];
		model.fields = new Field[rows[0].length()][rows.length];

		for (int i = 0; i < model.graphicsFields.length; ++i) {
			for (int j = 0; j < model.graphicsFields[i].length; ++j) {
				if (i < rows.length && j < rows[j].length()) {
					initializeField(rows, i, j);
				}
			}
		}
	}

	private void initializeField(String[] rows, int i, int j) {
		switch (rows[i].charAt(j)) {
			case 'W':
				initializeWarehouse(i, j);
				break;
			case 'L':
				initializeLaboratory(i, j);
				break;
			case 'S':
				initializeSafehouse(i, j);
				break;
			case 'F':
			default:
				initializeEmptyField(i, j);
				break;
		}
	}

	private void initializeWarehouse(int i, int j) {
		Warehouse wh = new Warehouse(i, j);
		model.fields[i][j] = wh;
		model.graphicsFields[i][j] = new GraphicsWarehouse(i, j);
		GraphicsMaterial gm = new GraphicsMaterial(wh.material, model.graphicsFields[i][j]);
		model.getGraphicsMaterial().add(gm);
	}

	private void initializeLaboratory(int i, int j) {
		model.fields[i][j] = new Laboratory(i, j);
		model.graphicsFields[i][j] = new GraphicsLaboratory(i, j);
	}

	private void initializeSafehouse(int i, int j) {
		Safehouse sf = new Safehouse(i, j);
		model.fields[i][j] = sf;
		model.graphicsFields[i][j] = new GraphicsSafehouse(i, j);
		switch (sf.gear.name) {
			case CLOAK:
				model.getGraphicsGear().add(new GraphicsCloak(sf, model.graphicsFields[i][j]));
				break;
			case GLOVES:
				model.getGraphicsGear().add(new GraphicsGloves(sf, model.graphicsFields[i][j]));
				break;
			case SACK:
				model.getGraphicsGear().add(new GraphicsSack(sf, model.graphicsFields[i][j]));
				break;
			case AXE:
				model.getGraphicsGear().add(new GraphicsAxe(sf, model.graphicsFields[i][j]));
				break;
		}
	}

	private void initializeEmptyField(int i, int j){
		model.fields[i][j] = new Field(i, j);
		model.graphicsFields[i][j] = new GraphicsField(i, j);
	}

	private void initializeFields() {
		for (int i = 0; i < model.fields.length; ++i) {
			for (int j = 0; j < model.fields[i].length; ++j) {
				if (model.fields[i][j] != null) {
					connectNeighbours(i, j);
				}
			}
		}
	}

	private void connectNeighbours(int i, int j) {
		if (i < model.fields.length - 1 && (model.fields[i + 1][j] != null)) {
			model.fields[i][j].getNeighbours().add(model.fields[i + 1][j]);
		}
		if (i > 0 && (model.fields[i - 1][j] != null)) {
			model.fields[i][j].getNeighbours().add(model.fields[i - 1][j]);
		}
		if (j > 0 && (model.fields[i][j - 1] != null)) {
			model.fields[i][j].getNeighbours().add(model.fields[i][j - 1]);
		}
		if (j < model.fields[i].length - 1 && (model.fields[i][j + 1] != null)) {
			model.fields[i][j].getNeighbours().add(model.fields[i][j + 1]);
		}
	}

	private void connectNeighbours() {
		for (int i = 0; i < model.fields.length; ++i) {
			for (int j = 0; j < model.fields[i].length; ++j) {
				if (model.fields[i][j] != null) {
					connectNeighbours(i, j);
				}
			}
		}
	}

	private void generateRandomFields() {
		int length = rand.nextInt(15 - 10) + 10;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			for (int j = 0; j < length; ++j) {
				int random = rand.nextInt(100);
				if (random < 3) {
					sb.append("S");
				} else if (random >= 3 && random < 6) {
					sb.append("W");
				} else if (random >= 6 && random < 11) {
					sb.append("L");
				} else {
					sb.append("F");
				}
			}
			sb.append(";");
		}
		generateFieldsFromMapMatrix(sb.toString());
	}

	private void addPanels() {
		int pLen = 1000 / model.graphicsFields.length;
		for (int i = 0; i < model.graphicsFields.length; ++i) {
			for (int j = 0; j < model.graphicsFields[i].length; ++j) {
				if (model.graphicsFields[i][j] != null) {
					balJp.add(model.graphicsFields[i][j]);
					model.graphicsFields[i][j].setBounds(pLen * j, pLen * i, pLen, pLen);
				}
			}
		}
		refreshView();
	}

	// Ide tartozik a HUD többi elemének rajzolása/kezelése is
	public void refreshView() {
		view.repaintAll();
		if (v != null) {
			updateLabels();
		}
		this.invalidate();
		this.repaint();
	}

	public void virologistFrame(final List<Character> virList, final String action) {
		final JFrame jf = new JFrame("random_csapatnev virologist_frame");
		jf.setSize(250, 350);
		jf.setLayout(new FlowLayout());

		for (Character s : virList) {
			JButton item = new JButton(s.name);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Character c = findCharacterByName(virList, e.getActionCommand());

					if (c != null) {
						handleAction(c, action);
					}
					jf.dispose();
				}
			});
			item.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 25));
			jf.add(item);
		}

		jf.setResizable(false);
		jf.setLocationRelativeTo(frame);
		jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	private Character findCharacterByName(List<Character> characterList, String name) {
		for (Character character : characterList) {
			if (character.name.equals(name)) {
				return character;
			}
		}
		return null;
	}

	private void handleAction(Character character, String action) {
		switch (action) {
			case "useAgent":
				agentPickerFrame(character);
				break;
			case "stealMaterial":
				v.stealMaterialInteract(character);
				break;
			case "stealGear":
				v.stealGearInteract(character);
				break;
			case "benit":
				character.setIsParalyzed(true);
				break;
			case "useAxe":
				if (character.name.startsWith("b")) {
					for (Gear gear : v.activeGears) {
						if (gear.name == GearEnum.AXE && Boolean.TRUE.equals(gear.canUse)) {
							gear.effect(character);
						}
					}
				}
				break;
			default:
				break;
		}
		refreshView();
	}

	public void agentPickerFrame(final Character c) {
		final JFrame jf = new JFrame("random_csapatnev agent_picker_frame");
		jf.setSize(250, 350);
		jf.setLayout(new FlowLayout());

		ArrayList<JButton> buttList = new ArrayList<>();

		for (Agent s : v.craftedAgents) {
			JButton item = new JButton(s.name);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Agent a = null;
					for (Agent agent : v.craftedAgents) {
						if (agent.name.equals(e.getActionCommand())) {
							a = agent;
						}
					}
					if (a != null) {
						v.use(c, a);
					}
					refreshView();
					jf.dispose();
				}
			});
			item.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 25));
			jf.add(item);
			buttList.add(item);
		}

		jf.setResizable(false);
		jf.setLocationRelativeTo(frame);
		jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	public void agentCraftPickerFrame() {
		final JFrame jf = new JFrame("random_csapatnev agent_craft_picker_frame");
		jf.setSize(250, 350);
		jf.setLayout(new FlowLayout());

		ArrayList<JButton> buttList = new ArrayList<>();

		for (Agent s : v.knownAgents) {
			JButton item = new JButton(s.name);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Agent a = null;
					for (Agent agent : v.knownAgents) {
						if (agent.name.equals(e.getActionCommand())) {
							a = agent;
						}
					}
					if (a != null) {
						v.craftAgent(a);
					}
					refreshView();
					jf.dispose();
				}
			});
			item.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 25));
			jf.add(item);
			buttList.add(item);
		}

		jf.setResizable(false);
		jf.setLocationRelativeTo(frame);
		jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	public void gearPickerFrame() {
		final JFrame jf = new JFrame("random_csapatnev gear_picker_frame");
		jf.setSize(250, 350);
		jf.setLayout(new FlowLayout());

		addGearButtons(jf, v.gears, false);
		addGearButtons(jf, v.activeGears, true);

		jf.setResizable(false);
		jf.setLocationRelativeTo(frame);
		jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	private void addGearButtons(final JFrame inputFrame, List<Gear> gears, final boolean isActive) {
		for (final Gear gear : gears) {
			JButton button = new JButton(gear.name.toString());
			button.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 25));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					handleGearAction(inputFrame, gear, isActive);
				}
			});
			if (isActive) {
				button.setForeground(Color.RED);
			}
			inputFrame.add(button);
		}
	}

	private void handleGearAction(JFrame inputFrame, Gear gear, boolean isActive) {
		List<Gear> gearList = isActive ? v.activeGears : v.gears;
		Gear selectedGear = findGearByName(gearList, gear.name);
		if (selectedGear != null) {
			if (isActive) {
				v.unequipGear(selectedGear.name);
			} else {
				v.equipGear(selectedGear.name);
			}
		}
		refreshView();
		inputFrame.dispose();
	}

	private Gear findGearByName(List<Gear> gearList, GearEnum gearName) {
		for (Gear gear : gearList) {
			if (gear.name == gearName) {
				return gear;
			}
		}
		return null;
	}

	public GearEnum stringToGearEnum(String s) {
		switch (s) {
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

	public void movePickerFrame(boolean fel, boolean jobb, boolean le, boolean bal) {
		final JFrame jf = new JFrame("random_csapatnev movePickerFrame");
		jf.setSize(200, 250);
		jf.setLayout(new BorderLayout());

		JButton jbtFel = new JButton(new AbstractAction("↑") {
			public void actionPerformed(ActionEvent ae) {
				v.move(model.fields[v.currField.x - 1][v.currField.y]);
				refreshView();
				jf.dispose();
			}
		});

		JButton jbtJobb = new JButton(new AbstractAction("→") {
			public void actionPerformed(ActionEvent ae) {
				v.move(model.fields[v.currField.x][v.currField.y + 1]);
				refreshView();
				jf.dispose();
			}
		});

		JButton jbtLe = new JButton(new AbstractAction("↓") {

			public void actionPerformed(ActionEvent ae) {
				v.move(model.fields[v.currField.x + 1][v.currField.y]);
				refreshView();
				jf.dispose();
			}
		});

		JButton jbtBal = new JButton(new AbstractAction("←") {
			public void actionPerformed(ActionEvent ae) {
				v.move(model.fields[v.currField.x][v.currField.y - 1]);

				refreshView();
				jf.dispose();
			}
		});

		jbtFel.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 25));
		jbtJobb.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 25));
		jbtLe.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 25));
		jbtBal.setFont(new Font(StringLiterals.FONT_NAME, Font.BOLD, 25));

		jbtFel.setEnabled(fel);
		jbtJobb.setEnabled(jobb);
		jbtLe.setEnabled(le);
		jbtBal.setEnabled(bal);

		jf.add(jbtFel, BorderLayout.PAGE_START);
		jf.add(jbtJobb, BorderLayout.LINE_END);
		jf.add(jbtLe, BorderLayout.PAGE_END);
		jf.add(jbtBal, BorderLayout.LINE_START);

		jf.setResizable(false);
		jf.setLocationRelativeTo(jbtMove);
		jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}

	public void updateLabels() {
		actualField.setText(String.format("Aktuális Mező: (%s;%s)", v.currField.x, v.currField.y));

		actualMaterial.setText("Aktuális Anyagok:");
		aminoacid.setText(String.format("    -Aminosav: %s/%s", v.currMaterial.container.get(MatEnum.AMINOACID),
				v.maxMaterial.container.get(MatEnum.AMINOACID)));
		nucleotide.setText(String.format("    -Nukleotid: %s/%s", v.currMaterial.container.get(MatEnum.NUCLEOTIDE),
				v.maxMaterial.container.get(MatEnum.NUCLEOTIDE)));

		StringBuilder sb = new StringBuilder();
		sb.append("Aktív felszerelések: ");
		for (Gear a : v.activeGears) {
			if (a.name == GearEnum.AXE) {
				if (Boolean.TRUE.equals(a.canUse)) {
					sb.append(a.name + ", ");
				} else {
					sb.append(a.name + "(B), ");
				}
			} else {
				sb.append(a.name + ", ");
			}

		}
		activeGears.setText(sb.toString().substring(0, sb.toString().length() - 2));

		sb.setLength(0);
		sb.append("Inaktív felszerelések: ");

		for (Gear a : v.gears) {
			sb.append(a.name + ", ");
		}
		passiveGears.setText(sb.toString().substring(0, sb.toString().length() - 2));

		sb.setLength(0);
		sb.append("Aktív ágensek: ");
		for (Agent a : v.activeAgents) {
			sb.append(a.name + ", ");
		}
		activeAgents.setText(sb.toString().substring(0, sb.toString().length() - 2));

		sb.setLength(0);
		sb.append("Megtanult ágensek: ");
		for (Agent a : v.knownAgents) {
			sb.append(a.name + ", ");
		}
		knownAgents.setText(sb.toString().substring(0, sb.toString().length() - 2));

		sb.setLength(0);
		sb.append("Craftolt ágensek: ");
		for (Agent a : v.craftedAgents) {
			sb.append(a.name + ", ");
		}
		craftedAgents.setText(sb.toString().substring(0, sb.toString().length() - 2));

		vitusEffect.setText(String.format("Vitustánc hatás?: %s", v.isVitus));
		paralyzedEffect.setText(String.format("Bénult hatás?: %s", v.isParalyzed));
	}

	public void overallRound() {
		round();
		aiRound();
	}

	public void round() {
		ArrayList<Character> tempList = new ArrayList<>(model.getCharacters());
		for (Character c : tempList) {
			c.round();
		}
	}

	public void aiRound() {
		ArrayList<Character> tempList = new ArrayList<>(model.getCharacters());
		for (Character c : tempList) {
			if (!c.name.equals("v0")) {
				List<Field> neighFields = c.currField.getNeighbours();
				int randomint = rand.nextInt(neighFields.size());
				c.move(neighFields.get(randomint));
				c.fieldInteract();
			}
		}
	}

	public void endCheck() {
		for (Character c : model.getCharacters()) {
			if (c.knownAgents.size() == winCon.size()) {
				if (c.name.equals("v0")) {
					gameRunning = false;
					Main.endGame(true);
				} else {
					gameRunning = false;
					Main.endGame(false);
				}
			}
		}
	}
}
