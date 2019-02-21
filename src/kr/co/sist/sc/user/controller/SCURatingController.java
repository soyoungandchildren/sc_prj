package kr.co.sist.sc.user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.co.sist.sc.user.model.SCUMovieDAO;
import kr.co.sist.sc.user.view.SCURatingView;
import kr.co.sist.sc.user.view.SCUWriteRatingView;
import kr.co.sist.sc.user.vo.SCUSearchRatingVO;

public class SCURatingController extends WindowAdapter implements ActionListener {
	
	private SCURatingView srv;
	
	public SCURatingController(SCURatingView srv) {
		this.srv = srv;
		searchRating();
	}//Constructor

	
	public void searchRating() {
		List<SCUSearchRatingVO> list = new ArrayList<>();
		
		
		try {
			list = SCUMovieDAO.getInstance().selectRatingData(srv.getSmlv().getSelectedMovieCode());
			
			if(list.size()==0) {
				JOptionPane.showMessageDialog(srv, "��ϵ� �������� �����ϴ�!");
			}else {
				
				Object[] objArr = new Object[3];
				JPanel jpel = new JPanel();
				jpel.add(new JLabel(new ImageIcon("C:\\dev\\workspace\\sc_prj\\src\\kr\\co\\sist\\sc\\user\\images\\imageicon_star_perfect(22x22).png")));
				for(int i =0; i<list.size(); i++) {
					
					objArr[0] = jpel;
					objArr[1] = list.get(i).getReview();
					objArr[2] = list.get(i).getMember_id();
					
					srv.getDtmRatingTable().addRow(objArr);
					
				}//end for
				
			}//end if
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end try catch
		
	}//searchRating
	
	
	private boolean isLogin() {
		boolean result = false;
		
		result = srv.getSmlv().getSmv().getIsLogin();
		
		return result;
	}//isLogin Method
	
	private void canWrite() {
		List<String> listBookNumber = new ArrayList<>();
		
		String idConnecting = srv.getSmlv().getSmv().getIdConnecting();
		String selectedMovieCode = srv.getSmlv().getSelectedMovieCode();
		
		try {
			listBookNumber = SCUMovieDAO.getInstance().didWatch(idConnecting, selectedMovieCode);
			if(listBookNumber.size() == 0) {
				JOptionPane.showMessageDialog(srv, "��ȭ�� �����ϼž� ������ ���Ⱑ �����մϴ�.");
			}else {
				String bookNumber = SCUMovieDAO.getInstance().didWrite(listBookNumber);
				if(bookNumber.equals("")) {
					JOptionPane.showMessageDialog(srv, "�̹� ��ȭ�� ���� �������� �ۼ��ϼ̽��ϴ�.");
				}else {
					new SCUWriteRatingView(srv, bookNumber);
				}//end if~else
				
			}//if~else
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//didWatch Method
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(srv.getJbtnWriteRating())) {
			
			if(isLogin()) {
				canWrite();
			}else {
				JOptionPane.showMessageDialog(srv, "�α����� ���� ���ּ���.");
			}//end if else
			
		}//end if
		
		if(ae.getSource().equals(srv.getJbtnClose())) {
			srv.dispose();
		}//end if

	}//actionPerformed Override
	
	@Override
	public void windowClosing(WindowEvent e) {
		srv.dispose();
	}//windowClosing Override

	
}//Class