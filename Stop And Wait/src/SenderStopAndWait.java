package simullc.protocol;

import simullc.entity.ProtocolEntity;
import simullc.entity.TimeoutException;
import simullc.gui.NodeGraph;
import simullc.gui.Trame;

/*
* Internet Simulator by Madyne
* Copyright (C) 2001 Emmanuel Nataf, Olivier Festor
*               LORIA - Universite de Nancy2 - INRIA
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
*  This library is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
*  Lesser General Public License for more details.
*
*  You should have received a copy of the GNU Lesser General Public
*  License along with this library; if not, write to the Free Software
*  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*
*  Contact : nataf@loria.fr
*            festor@loria.fr
*/
public class SenderStopAndWait extends ProtocolEntity {

    private int delay;

    public SenderStopAndWait(NodeGraph n, String m, int d){
	super(n,m);
	delay = d;
    }

    public void run()
	{
	    int i = 0;
		Trame t = readData();
		char c;
		char ancienc = ' ';
		boolean reseaucoupé = false;
		int nbessai = 0;
		c = getData(t);
		i = 1;
		markTrame(t, i);		
		writeData(t);		
		display("Message en cours d'envoi.");
		
		while(!reseaucoupé)
		{
			sendTrame(t);
			boolean tramearrive = false;
			nbessai = 0;
			
			while(!tramearrive && !reseaucoupé)
			{				
				try
				{
					Trame tretour = receiveTrame(10);				
					
					if(extractTrameNumber(tretour) == i)
						tramearrive = true;
				}
				catch(TimeoutException tex)
				{
					display("un dépassement de délai s\'est produit");
					nbessai++;
					sendTrame(t);
					
					if(nbessai > 5)
					{
						reseaucoupé = true;
						display("Le réseau est coupé...");
					}
				}
			}
			
			if(c == '#')
			{			
				if(ancienc != '\\')
				{
					break; 
				}
			}			

			if(!reseaucoupé)
			{
				ancienc = getData(t);
				t = readData();
				c = getData(t);
				i = 1 - i;	// Transforme 1 en 0 et 0 en 1
				markTrame(t, i);			
				writeData(t);
			}
		}
    }
}
