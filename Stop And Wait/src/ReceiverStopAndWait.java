package simullc.protocol;

import simullc.entity.ProtocolEntity;
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
public class ReceiverStopAndWait extends ProtocolEntity {

    private int delay;

    public ReceiverStopAndWait(NodeGraph n,int d){
	super(n);
	delay = d;
    }

    public void run()
	{
		int nombreattendu = 1;
		int nombreobtenu = -1;		
		Trame anciennet = null;
		Trame t = receiveTrame();
		char ch = ' ';
		char chbefore = '$';
		display("Message en cours de réception");
		
		while(!isEnded())
		{
			ch = getData(t);
			nombreobtenu = extractTrameNumber(t);			
			
			if(nombreobtenu != nombreattendu)
			{
				display("Recu une trame que le receveur n'aurait pas du recevoir.");
				Trame tretour = new Trame('A');
				markTrame(tretour, nombreobtenu);
				sendTrame(tretour);
			}
			else
			{			
				Trame tretour = new Trame('A');
				markTrame(tretour, nombreobtenu);
				sendTrame(tretour);			
				nombreattendu = 1 - nombreattendu;

				if(anciennet != null)
				{
					if((chbefore == '\\') && (ch != '#'))
						writeData(anciennet);
					else if(chbefore != '\\')
						writeData(anciennet);
				}
				
				chbefore = getData(t);
				anciennet = t;
			}
			
			t = receiveTrame();
		}
		
		display("Message recu");
    }
}
