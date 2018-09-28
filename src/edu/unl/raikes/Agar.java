package edu.unl.raikes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;

public class Agar extends GraphicsProgram{

	GOval player;
	GOval items;
	GOval ai;
	GLabel click;
	GLabel raikes;
	int score = 0;
	double dx = 2;
	double dy = 2;
	double aidx = 3;
	double aidy = 3;
	double aidx2 = 3;
	double aidy2 = 3;
	double aidx3 = 3;
	double aidy3 = 3;
	double aidx4 = 3;
	double aidy4 = 3;
	double aidx5 = 3;
	double aidy5 = 3;
	double aidx6 = 3;
	double aidy6 = 3;
	double aidx7 = 3;
	double aidy7 = 3;
	double aidx8 = 3;
	double aidy8 = 3;
	double spawn_timer = 0;
	private static final int PAUSE = 30;
	private static final int ITEMS = 100;
	private static final int ITEM_SIZE = 10;
	private static final int PLAYER_SIZE = 20;
	private static final int GREEN = 12;
	private static final int AI_NUMBER = 5;
	private static final int AI_SIZE = 30;
	private static final int GREEN_SIZE = 60;
	private ArrayList<GOval> ovals = new ArrayList<GOval>();
	private ArrayList<GOval> ovalsG = new ArrayList<GOval>();
	private ArrayList<GOval> AIlist = new ArrayList<GOval>();
	GOval ai1;
	GOval ai2;
	GOval ai3;
	GOval ai4;
	GOval ai5;
	boolean end = false;
	
	public void run()
	{
	addMouseListeners();
	waitForClick();
	remove(click);
	while (true)
	{
	move();
	collisiontest();
	AI();
	outOfBounds();
	eat();
	add();
	end();
	}


	}
	public void init()
	{
	setSize(1400,770);
	GImage black = new GImage("images.png",0, 0);
	black.setSize(getWidth(), getHeight());
	add(black);

	raikes = new GLabel ("Raikes 2017 / Tim Roty");
	raikes.setColor(Color.GRAY);
	double x2 = (getWidth()/2 - raikes.getWidth() - 100);
	double y2 = (getHeight()/2);
	raikes.setFont(new Font("Serif", Font.BOLD, 48));
	add(raikes, x2, y2);

	player = new GOval(50,50,PLAYER_SIZE,PLAYER_SIZE);
	player.setFillColor(Color.BLUE);
	player.setFilled(true);
	add(player);

	for (int i = 0; i < ITEMS; i++){
	RandomGenerator rgen = RandomGenerator.getInstance();
	double x = rgen.nextDouble(0,getWidth());
	double y = rgen.nextDouble(0, getHeight());
	GOval items = new GOval(x,y,ITEM_SIZE, ITEM_SIZE);
	Color color = rgen.nextColor();
	items.setFillColor(color);
	items.setFilled(true);
	ovals.add(items);
	add(items);
	}

	for (int i = 0; i < GREEN; i++)
	{
	RandomGenerator rgen = RandomGenerator.getInstance();
	double x = rgen.nextDouble(0,getWidth());
	pause(PAUSE);
	double y = rgen.nextDouble(0, getHeight());
	pause(PAUSE);
	GOval green = new GOval(x,y,GREEN_SIZE, GREEN_SIZE);
	green.setFillColor(Color.GREEN);
	green.setFilled(true);
	ovalsG.add(green);
	add(green);
	}

	for (int i = 0; i < AI_NUMBER; i++)
	{
	RandomGenerator rgen = RandomGenerator.getInstance();
	double x = rgen.nextDouble(100,getWidth() - 100);
	double y = rgen.nextDouble(100, getHeight()-100);
	ai = new GOval(x,y,AI_SIZE, AI_SIZE);
	ai.setFillColor(Color.RED);
	ai.setFilled(true);
	AIlist.add(ai);
	add(ai);
	}
	ai1 = AIlist.get(0);
	ai2 = AIlist.get(1);
	ai3 = AIlist.get(2);
	ai4 = AIlist.get(3);
	ai5 = AIlist.get(4);

	click = new GLabel ("Click to begin.");
	click.setColor(Color.WHITE);
	double x1 = (getWidth()/2 - click.getWidth() - 50);
	double y1 = (getHeight()/2 - 50);
	click.setColor(Color.white);
	click.setFont(new Font("Serif", Font.BOLD, 48));
	add(click, x1, y1);

	}

	public void move()
	{
	player.move(dx,dy);
	ai1.move(aidx,aidy);
	ai2.move(aidx2, aidy2);
	ai3.move(aidx3, aidx3);
	ai4.move(aidx4,aidx4);
	ai5.move(aidx5, aidy5);
	pause(PAUSE);
	}

	public void collisiontest()
	{
	for (int i=0; i < ovals.size(); i++){
	GOval checkOval = ovals.get(i);
	if (player.contains(checkOval.getX(), checkOval.getY()))
	{
	remove(checkOval);
	player.setSize(player.getWidth() + .1, player.getHeight() + .1);

	}
	}
	for (int i=0; i < ovalsG.size(); i++){
	GOval checkOval = ovalsG.get(i);
	if (player.contains(checkOval.getX(), checkOval.getY()) && player.getWidth() > checkOval.getWidth())
	{

	player.setSize(player.getWidth() - 30, player.getHeight() - 30);
	}

	}
	for (int j = 0; j < AIlist.size(); j++)
	{
	GOval ai = AIlist.get(j);
	for (int i = 0; i < ovals.size(); i++){
	GOval checkOval = ovals.get(i);
	if (ai.contains(checkOval.getX(), checkOval.getY()) && ai.getWidth() > checkOval.getWidth())
	{
	remove(checkOval);
	ai.setSize(ai.getWidth() + .1, ai.getHeight() + .1);
	}
	}
	}
	for (int j = 0; j < AIlist.size(); j++)
	{
	GOval ai = AIlist.get(j);
	for (int i =0; i < ovalsG.size(); i++){
	GOval checkOval = ovalsG.get(i);
	if (ai.contains(checkOval.getX(), checkOval.getY()) && ai.getWidth() > checkOval.getWidth())
	{
	ai.setSize(ai.getWidth() - 30, ai.getHeight() - 30);
	}
	}
	}
	for (int j = 0; j < AIlist.size(); j++)
	{
	GOval ai = AIlist.get(j);
	for (int i =0; i < ovalsG.size(); i++){
	GOval checkOval = ovalsG.get(i);
	if (ai.contains(checkOval.getX(), checkOval.getY()) && ai.getWidth() > checkOval.getWidth())
	{
	ai.setSize(ai.getWidth() - 30, ai.getHeight() - 30);
	}
	}
	}
	}
	public void AI()
	{
	AI1();
	AI2();
	AI3();
	AI4();
	AI5();
	//	AI6();
	//	AI7();
	//	AI8();
	}
	public void AI1()
	{
	RandomGenerator rgen = RandomGenerator.getInstance();
	int x = rgen.nextInt(0, 40);
	int y = rgen.nextInt(0, 40);
	if (x == 1)
	{
	aidx = -aidx;
	}
	if (y ==1)
	{
	aidy= -aidy;
	}
	}
	public void AI2()
	{
	RandomGenerator rgen = RandomGenerator.getInstance();
	int x = rgen.nextInt(0, 40);
	int y = rgen.nextInt(0, 40);
	if (x == 1)
	{
	aidx2 = -aidx2;
	}
	if (y ==1)
	{
	aidy2= -aidy2;
	}

	}
	public void AI3()
	{
	RandomGenerator rgen = RandomGenerator.getInstance();
	int x = rgen.nextInt(0, 40);
	int y = rgen.nextInt(0, 40);
	if (x == 1)
	{
	aidx3 = -aidx3;
	}
	if (y ==1)
	{
	aidy3= -aidy3;
	}
	}
	public void AI4()
	{
	RandomGenerator rgen = RandomGenerator.getInstance();
	int x = rgen.nextInt(0, 40);
	int y = rgen.nextInt(0, 40);
	if (x == 1)
	{
	aidx4 = -aidx4;
	}
	if (y ==1)
	{
	aidy4= -aidy4;
	}
	}
	public void AI5()
	{
	RandomGenerator rgen = RandomGenerator.getInstance();
	int x = rgen.nextInt(0, 40);
	int y = rgen.nextInt(0, 40);
	if (x == 1)
	{
	aidx5 = -aidx5;
	}
	if (y ==1)
	{
	aidy5= -aidy5;
	}
	}

	public void outOfBounds()
	{
	if (ai1.getX()<0 || ai1.getX() > getWidth())
	{
	aidx = -aidx;
	}
	if (ai2.getX()<0 || ai2.getX() > getWidth())
	{
	aidx2 = -aidx2;
	}
	if (ai3.getX()<0 || ai3.getX() > getWidth())
	{
	aidx3 = -aidx3;
	}
	if (ai4.getX()<0 || ai4.getX() > getWidth())
	{
	aidx4 = -aidx4;
	}
	if (ai5.getX()<0 || ai5.getX() > getWidth())
	{
	aidx5 = -aidx5;
	}
	if (ai1.getY()<0 || ai1.getY() > getHeight())
	{
	aidy = -aidy;
	}
	if (ai2.getY()<0 || ai2.getY() > getHeight())
	{
	aidy2 = -aidy2;
	}
	if (ai3.getY()<0 || ai3.getY() > getHeight())
	{
	aidy3 = -aidy3;
	}
	if (ai4.getY()<0 || ai4.getY() > getHeight())
	{
	aidy4 = -aidy4;
	}
	if (ai5.getY()<0 || ai5.getY() > getHeight())
	{
	aidy5 = -aidy5;
	}
	}
	public void eat()
	{
	for (int j = 0; j < AIlist.size(); j++)
	{
	GOval ai = AIlist.get(j);
	if (player.contains(ai.getX(), ai.getY()) && player.getWidth() > ai.getWidth())
	{
	remove(ai);
	player.setSize(player.getWidth() + 1, player.getHeight() + 1);
	score ++;
	AIlist.remove(j);
	}
	if (ai.contains(player.getX(), player.getY()) && ai.getWidth() > player.getWidth())
	{
	remove(player);
	ai.setSize(ai.getWidth() + 1, ai.getHeight() + 1);
	end = true;
	}
	}
	
	for (int j = 0; j < AIlist.size(); j++)
	{
	GOval ai = AIlist.get(j);
	for (int i = 0; i < AIlist.size(); i++){
	GOval checkOval = AIlist.get(i);
	if (ai != checkOval)
	{
	if (ai.contains(checkOval.getX(), checkOval.getY()) && ai.getWidth() > checkOval.getWidth())
	{
	remove(checkOval);
	ai.setSize(ai.getWidth() + 1, ai.getHeight() + 1);
	score ++;
	AIlist.remove(i);
	}
	}

	}
	}
	}

	public void add()
	{
	spawn_timer ++;
	if (spawn_timer >= 50)
	{
	RandomGenerator rgen = RandomGenerator.getInstance();
	double x = rgen.nextDouble(0,getWidth());
	double y = rgen.nextDouble(0, getHeight());
	GOval items = new GOval(x,y,ITEM_SIZE, ITEM_SIZE);
	Color color = rgen.nextColor();
	items.setFillColor(color);
	items.setFilled(true);
	ovals.add(items);
	add(items);
	spawn_timer = 0;
	}
	}
	@Override public void mouseMoved(MouseEvent e)
	{

	double cursorx = e.getX() + PLAYER_SIZE/2;
	double cursory = e.getY() + PLAYER_SIZE/2;


	if (cursorx < player.getX() + player.getWidth()/2)
	{
	dx= -2;
	}
	else if (cursorx > player.getX() + player.getWidth()/2)
	{
	dx= 2;
	}
	else {
	dx = 0;
	}
	if (cursory > player.getY() + player.getHeight()/2)
	{
	dy = 2;
	}
	else if (cursory < player.getY() + player.getHeight()/2)
	{
	dy= -2;
	}
	else 
	{
	dy = 0;
	}

	}

	public void end()
	{
	if (end == true)
	{
	remove(raikes);
	GLabel end = new GLabel ("Game Over");
	end.setColor(Color.WHITE);
	double x1 = (getWidth()/2 - end.getWidth() - 50);
	double y1 = (getHeight()/2);
	end.setFont(new Font("Serif", Font.BOLD, 48));
	add(end, x1, y1);

	waitForClick();
	}
	if (score == 5)
	{
	remove(raikes);
	GLabel win = new GLabel ("You Win");
	win.setColor(Color.WHITE);
	double x1 = (getWidth()/2 - win.getWidth() - 50);
	double y1 = (getHeight()/2);
	win.setFont(new Font("Serif", Font.BOLD, 48));
	add(win, x1, y1);

	waitForClick();
	}
	}
}


