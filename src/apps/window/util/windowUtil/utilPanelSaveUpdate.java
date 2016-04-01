package apps.window.util.windowUtil;

import java.util.Collection;
import java.util.Vector;

import productPricing.Pricer;

import beans.Trade;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import apps.window.tradewindow.CommonPanel;
import apps.window.tradewindow.TradePanel;

public class utilPanelSaveUpdate extends CommonPanel{
	
	
	RemoteReferenceData remoteRerenceData;
	RemoteTrade remoteTrade;
	TradePanel tradePanel;
	CommonPanel productWindowpanel = null;
	
	public utilPanelSaveUpdate() {
		initComponents();
	}
	
	
	private void initComponents() {
		
		 jPanel5 = new javax.swing.JPanel();
		 NEW = new javax.swing.JButton();
		 SAVE = new javax.swing.JButton();
		 SAVEASNEW = new javax.swing.JButton();
		 DELETE = new javax.swing.JButton();
	        NEW.setText("NEW");

	        SAVE.setText("SAVE");

	        SAVEASNEW.setText("SAVE AS NEW");

	        DELETE.setText("DELETE");
    this.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(this);
    this.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NEW)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SAVE, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SAVEASNEW)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DELETE)
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {NEW, SAVE, SAVEASNEW, DELETE});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NEW, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SAVE, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DELETE)
                        .addComponent(SAVEASNEW))))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {NEW, SAVE, SAVEASNEW, DELETE});

	}
	
	 public javax.swing.JButton NEW;
	    public javax.swing.JButton SAVE;
	    public javax.swing.JButton SAVEASNEW;
	    public javax.swing.JButton DELETE;
	    public javax.swing.JPanel jPanel5;

		public RemoteReferenceData getRemoteRerenceData() {
			return remoteRerenceData;
		}


		public void setRemoteRerenceData(RemoteReferenceData remoteRerenceData) {
			this.remoteRerenceData = remoteRerenceData;
		}


		public RemoteTrade getRemoteTrade() {
			return remoteTrade;
		}


		public void setRemoteTrade(RemoteTrade remoteTrade) {
			this.remoteTrade = remoteTrade;
		}


		public TradePanel getTradePanel() {
			return tradePanel;
		}


		public void setTradePanel(TradePanel tradePanel) {
			this.tradePanel = tradePanel;
		}


		public CommonPanel getProductWindowpanel() {
			return productWindowpanel;
		}


		public void setProductWindowpanel(CommonPanel productWindowpanel) {
			this.productWindowpanel = productWindowpanel;
		}
		@Override
		public void buildTrade(Trade trade,String actionType) {
			
			
			
		}
		@Override
		public void openTrade(Trade trade) {
			
			
		}
		@Override
		 public void setPanelValue(CommonPanel tradeValue) {
			
		}


		


		@Override
		public Collection getCashFlows() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public Pricer getPricer() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void setTradePanelValue(CommonPanel tradeValue) {
			// TODO Auto-generated method stub
			
		}
}
