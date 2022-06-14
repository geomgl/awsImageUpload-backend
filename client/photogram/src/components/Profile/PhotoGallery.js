import React from 'react';
import profilePic from '../../assets/profilePic.png'
import './profile.scss'

function PhotoGallery() {
	function renderPhotos() {
		// wip, hard coded just to get styling
		let i = 0;
		let photos = [];
		while (i < 15) {
			photos.push({});
			i++;
		}

		return photos.map(() => {
			return (
				<img id='photo' src={profilePic} alt='profilePic' />
			)
		})
	}

	return (
		<div id='photosContainer'>
			{renderPhotos()}
		</div>
	)
}

export default PhotoGallery;